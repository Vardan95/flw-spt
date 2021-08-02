package com.vapetrosyan.flowrspot.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlowerListItemDto(
    val id: Long = -1,
    val name: String = "",
    val latinName: String = "",
    val sightings: Int = 0,
    @Json(name = "profile_picture")
    val profilePicture: String? = null,
    val favorite: Boolean = false
)
