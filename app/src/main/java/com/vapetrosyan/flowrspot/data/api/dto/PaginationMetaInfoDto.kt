package com.vapetrosyan.flowrspot.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationMetaInfoDto(
    val currentPage: Int = -1,
    @Json(name = "prev_page")
    val previousPage: Int? = null,
    @Json(name = "next_page")
    val nextPage: Int? = null,
    @Json(name = "total_pages")
    val totalPages: Int = 0,
)
