package com.vapetrosyan.flowrspot.data.model

data class FlowersListResult (
    val flowers: List<FlowerListItem> = emptyList(),
    val meta: PaginationMetaInfo
)