package com.vapetrosyan.flowrspot.data

import com.vapetrosyan.flowrspot.data.api.FlowrSpotApi
import com.vapetrosyan.flowrspot.data.api.dto.FlowersListResultDto
import com.vapetrosyan.flowrspot.data.model.FlowerListItem
import com.vapetrosyan.flowrspot.data.model.FlowersListResult
import com.vapetrosyan.flowrspot.data.model.PaginationMetaInfo
import javax.inject.Inject

class FlowersRepository @Inject constructor(
    private val flowersApi: FlowrSpotApi
) {
    suspend fun getFlowers(page : Int) {
        runCatching {
            val data = flowersApi.flowers(page = page)
            convertToModel(data) // Converters can be used
        }.getOrDefault(emptyList())
    }

    suspend fun searchFlowers(searchText: String? = null, page : Int) {
        runCatching {
            val data = flowersApi.searchFlowers(searchText = searchText, page = page)
            convertToModel(data) // Converters can be used
        }.getOrDefault(emptyList())
    }

    private fun convertToModel(dto: FlowersListResultDto): List<FlowerListItem> =
        dto.flowers.map { FlowerListItem(
            id = it.id,
            name = it.name,
            latinName = it.latinName,
            sightings = it.sightings,
            profilePicture = it.profilePicture,
            favorite = it.favorite
        ) }
}