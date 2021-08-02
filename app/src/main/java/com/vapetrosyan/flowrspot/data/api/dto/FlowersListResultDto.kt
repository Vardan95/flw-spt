package com.vapetrosyan.flowrspot.data.api.dto

data class FlowersListResultDto (
    val flowers: List<FlowerListItemDto> = emptyList(),
    val meta: PaginationMetaInfoDto
)