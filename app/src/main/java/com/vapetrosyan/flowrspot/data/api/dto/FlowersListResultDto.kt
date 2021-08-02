package com.vapetrosyan.flowrspot.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlowersListResultDto (
    val flowers: List<FlowerListItemDto> = emptyList(),
    val meta: FlowersListMetaDto
)