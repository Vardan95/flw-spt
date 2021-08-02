package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.vapetrosyan.flowrspot.data.model.FlowerListItem
import kotlinx.coroutines.flow.Flow

@Composable
fun FlowerListScreen(
    state: FlowersListContract.State,
    effectFlow: Flow<FlowersListContract.Effect>?,
    onEventSent: (event: FlowersListContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: FlowersListContract.Effect.Navigation) -> Unit
) {
    when (state) {
        is FlowersListContract.State.Initial -> ProgressIndicator()
        is FlowersListContract.State.Data -> FlowerList(pager = state.pager)
    }
}

@Composable
fun FlowerList(pager: Flow<PagingData<FlowerListItem>>) {
    val lazyPagingItems = pager.collectAsLazyPagingItems()

    LazyColumn {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        itemsIndexed(lazyPagingItems) { index, item ->
            Text("Index=$index: $item", fontSize = 20.sp)
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                ProgressIndicator()
            }
        }
    }
}

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}