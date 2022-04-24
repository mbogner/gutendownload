/*
 * Copyright 2022 mbo.dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.7" apply (false)
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply (false)
    kotlin("jvm") version "1.6.21" apply (false)
    kotlin("plugin.spring") version "1.6.21" apply (false)
}

val javaVersionStr: String by project
val gradleVersionProperty: String by project
val coroutinesVersion: String by project

allprojects {
    group = "dev.mbo"

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }

    apply(plugin = "io.spring.dependency-management")
    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
        resolutionStrategy {
            cacheChangingModulesFor(0, "seconds")
        }
        dependencies {
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-reactor
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-jdk8
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion")
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$coroutinesVersion")
            // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-slf4j
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:$coroutinesVersion")

            // https://mvnrepository.com/artifact/io.ktor/ktor-client-core
            dependency("io.ktor:ktor-client-core:2.0.0")
            // // https://mvnrepository.com/artifact/io.ktor/ktor-client-cio
            dependency("io.ktor:ktor-client-cio:2.0.0")

            // https://mvnrepository.com/artifact/com.github.tomakehurst/wiremock-jre8
            dependency("com.github.tomakehurst:wiremock-jre8:2.33.1")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersionStr
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.withType<Wrapper> {
    // https://gradle.org/releases/
    gradleVersion = gradleVersionProperty
    distributionType = Wrapper.DistributionType.ALL
}
