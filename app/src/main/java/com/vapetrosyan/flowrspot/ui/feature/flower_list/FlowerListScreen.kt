package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

@Composable
fun FlowerListScreen(
    state: FlowersListContract.State,
    effectFlow: Flow<FlowersListContract.Effect>?,
    onEventSent: (event: FlowersListContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: FlowersListContract.Effect.Navigation) -> Unit
) {

}