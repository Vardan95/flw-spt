package com.vapetrosyan.flowrspot.ui.feature.flower_list

import com.vapetrosyan.flowrspot.data.model.FlowersListResult
import com.vapetrosyan.flowrspot.ui.base.ViewEvent
import com.vapetrosyan.flowrspot.ui.base.ViewSideEffect
import com.vapetrosyan.flowrspot.ui.base.ViewState

class FlowersListContract {
    sealed class Event : ViewEvent {
        data class FlowerSelection(val id: Long) : Event()
        data class SearchFlower(val query: String?) : Event()
    }

    sealed class State : ViewState {
        object Empty : State()
        data class Data(val data: FlowersListResult) : State()
        data class Loading(val isLoading: Boolean) : State()
    }

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToFlowerDetails(val id: Long) : Navigation()
        }
    }

}