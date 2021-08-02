package com.vapetrosyan.flowrspot.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlowersListMetaDto(
    val pagination: PaginationMetaInfoDto
)