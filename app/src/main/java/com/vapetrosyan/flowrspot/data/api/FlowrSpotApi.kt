package com.vapetrosyan.flowrspot.data.api

import com.vapetrosyan.flowrspot.data.api.dto.FlowersListResultDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_VERSION = "api/v1/"

private const val FLOWERS = API_VERSION + "flowers"
private const val FLOWERS_SEARCH = API_VERSION + "flowers/search"

interface FlowrSpotApi {
    @GET(FLOWERS)
    suspend fun flowers(@Query("page") page: Int): FlowersListResultDto

    @GET(FLOWERS_SEARCH)
    suspend fun searchFlowers(@Query("query") searchText: String?, @Query("page") page: Int): FlowersListResultDto
}