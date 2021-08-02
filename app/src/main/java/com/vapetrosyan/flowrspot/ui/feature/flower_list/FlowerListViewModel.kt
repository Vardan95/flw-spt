package com.vapetrosyan.flowrspot.ui.feature.flower_list

import com.vapetrosyan.flowrspot.ui.base.BaseViewModel

class FlowerListViewModel : BaseViewModel<FlowersListContract.Event, FlowersListContract.State, FlowersListContract.Effect>() {
    override fun setInitialState(): FlowersListContract.State {
        return FlowersListContract.State.Loading(isLoading = true)
    }

    override fun processEvent(event: FlowersListContract.Event) {
        TODO("Not yet implemented")
    }
}