package com.vapetrosyan.flowrspot.data.model

data class FlowerListItem(
    val id: Long = -1,
    val name: String = "",
    val latinName: String = "",
    val sightings: Int = 0,
    val profilePicture: String? = null,
    val favorite: Boolean = false
)
