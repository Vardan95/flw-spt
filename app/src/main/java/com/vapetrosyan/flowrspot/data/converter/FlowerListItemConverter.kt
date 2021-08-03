package com.vapetrosyan.flowrspot.data.converter

import com.vapetrosyan.flowrspot.data.api.dto.FlowerListItemDto
import com.vapetrosyan.flowrspot.data.model.FlowerListItem
import javax.inject.Inject

class FlowerListItemConverter @Inject constructor() {
    fun dtoToModel(item: FlowerListItemDto) =
        FlowerListItem(
            id = item.id,
            name = item.name,
            latinName = item.latinName,
            sightings = item.sightings,
            profilePicture = if(item.profilePicture.orEmpty().startsWith("http")) item.profilePicture else
                "https:${item.profilePicture.orEmpty()}",
            favorite = item.favorite
        )
}