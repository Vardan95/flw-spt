package com.vapetrosyan.flowrspot.data

import com.vapetrosyan.flowrspot.data.api.FlowrSpotApi
import com.vapetrosyan.flowrspot.data.converter.FlowerListConverter
import javax.inject.Inject

class FlowerRepository @Inject constructor(
    private val flowersApi: FlowrSpotApi,
    private val converter: FlowerListConverter
) {
    suspend fun getFlowers(page: Int) =
        runCatching {
            val data = flowersApi.flowers(page = page)
            converter.dtoToModel(data).flowers
        }.getOrDefault(emptyList())

    suspend fun searchFlowers(searchText: String? = null, page: Int) =
        runCatching {
            val data = flowersApi.searchFlowers(searchText = searchText, page = page)
            converter.dtoToModel(data).flowers
        }.getOrDefault(emptyList())
}