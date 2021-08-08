package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.paging.Pager
import androidx.paging.PagingData
import com.vapetrosyan.flowrspot.data.model.FlowerListItem
import com.vapetrosyan.flowrspot.ui.base.ViewEvent
import com.vapetrosyan.flowrspot.ui.base.ViewSideEffect
import com.vapetrosyan.flowrspot.ui.base.ViewState
import kotlinx.coroutines.flow.Flow

class FlowersListContract {
    sealed class Event : ViewEvent {
        data class FlowerSelection(val id: Long) : Event()
        data class SearchFlower(val query: String?) : Event()
    }

    sealed class LoadingState {
        object None: LoadingState()
        data class Searching(val searchQuery: String?) : LoadingState()
    }

    sealed class State : ViewState {
        data class Data(val loadingState: LoadingState = LoadingState.None, val pager: Pager<Int, FlowerListItem>? = null) : State()
    }

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToFlowerDetails(val id: Long) : Navigation()
        }
    }
}