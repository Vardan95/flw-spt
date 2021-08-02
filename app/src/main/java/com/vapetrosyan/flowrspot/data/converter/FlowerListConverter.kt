package com.vapetrosyan.flowrspot.data.converter

import com.vapetrosyan.flowrspot.data.api.dto.FlowersListResultDto
import com.vapetrosyan.flowrspot.data.model.FlowersListResult
import com.vapetrosyan.flowrspot.data.model.PaginationMetaInfo
import javax.inject.Inject

class FlowerListConverter @Inject constructor(private val flowerListListItemConverter: FlowerListItemConverter) {
    fun dtoToModel(flowerList: FlowersListResultDto) =
        FlowersListResult(
            flowers = flowerList.flowers.map { flowerListListItemConverter.dtoToModel(it) },
            meta = PaginationMetaInfo(
                currentPage = flowerList.meta.currentPage,
                previousPage = flowerList.meta.previousPage,
                nextPage = flowerList.meta.nextPage,
                totalPages = flowerList.meta.totalPages,
            )
        )
}