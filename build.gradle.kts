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
