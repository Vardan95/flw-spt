package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.vapetrosyan.flowrspot.data.FlowerRepository
import com.vapetrosyan.flowrspot.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FlowerListViewModel @Inject constructor(private val flowerRepository: FlowerRepository) :
    BaseViewModel<FlowersListContract.Event, FlowersListContract.State, FlowersListContract.Effect>() {

    companion object {
        const val CONTACTS_PAGE_SIZE = 100
        const val SEARCH_DEBOUNCE_MILLIS = 200L
    }

    private val searchQuery = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            searchQuery.debounce(SEARCH_DEBOUNCE_MILLIS)
                .collectLatest {
                    loadFlowers(it)
                }
        }
    }

    override fun setInitialState(): FlowersListContract.State {
        return FlowersListContract.State.Data(FlowersListContract.LoadingState.None, null)
    }

    override fun processEvent(event: FlowersListContract.Event) {
        when (event) {
            is FlowersListContract.Event.SearchFlower -> {
                searchQuery.value = event.query
                emitState {
                    FlowersListContract.State.Data(
                        FlowersListContract.LoadingState.Searching(event.query)
                    )
                }
            }
            else -> {
                Timber.w("Unknown event passed to FlowerListViewModel $event")
            }
        }
    }

    private fun loadFlowers(searchText: String?) {
        emitState {
            FlowersListContract.State.Data(
                FlowersListContract.LoadingState.Searching(searchText),
                pager = Pager(
                    PagingConfig(
                        pageSize = CONTACTS_PAGE_SIZE,
                        initialLoadSize = CONTACTS_PAGE_SIZE * 2
                    )
                ) {
                    FlowerListPagingSource {
                        if (searchText == null) {
                            flowerRepository.getFlowers(it)
                        } else {
                            flowerRepository.searchFlowers(searchText = searchText, page = it)
                        }
                    }
                }
            )
        }
    }
}