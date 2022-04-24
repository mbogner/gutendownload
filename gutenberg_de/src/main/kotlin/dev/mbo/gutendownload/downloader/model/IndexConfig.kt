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

package dev.mbo.gutendownload.downloader.model

import java.util.regex.Pattern

data class IndexConfig(
    val windowStart: String?,
    val windowEnd: String?,
    val detailWindowStart: String?,
    val detailWindowEnd: String?,
    val replacements: List<Replacement>,

    val lineRegex: Pattern,
    val linePathIndex: Int,
    val lineNameIndex: Int,
)