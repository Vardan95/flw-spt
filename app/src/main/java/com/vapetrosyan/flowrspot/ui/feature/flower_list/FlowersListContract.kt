package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.paging.PagingData
import com.vapetrosyan.flowrspot.data.model.FlowerListItem
import com.vapetrosyan.flowrspot.ui.base.ViewEvent
import com.vapetrosyan.flowrspot.ui.base.ViewSideEffect
import com.vapetrosyan.flowrspot.ui.base.ViewState

class FlowersListContract {
    sealed class Event : ViewEvent {
        data class FlowerSelection(val id: Long) : Event()
        data class SearchFlower(val query: String?) : Event()
    }

    sealed class State : ViewState {
        object Initial : State()
        data class Data(val pager: PagingData<FlowerListItem>) : State()
    }

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToFlowerDetails(val id: Long) : Navigation()
        }
    }

}