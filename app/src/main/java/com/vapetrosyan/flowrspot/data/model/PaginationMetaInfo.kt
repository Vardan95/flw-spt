package com.vapetrosyan.flowrspot.data.model

data class PaginationMetaInfo(
    val currentPage: Int = -1,
    val previousPage: Int? = null,
    val nextPage: Int? = null,
    val totalPages: Int = 0,
)
