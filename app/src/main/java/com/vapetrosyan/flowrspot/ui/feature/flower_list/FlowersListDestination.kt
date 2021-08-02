package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun FlowersListDestination(navController: NavHostController) {
    val viewModel: FlowerListViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    FlowerListScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.handleEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when(navigationEffect) {
                is FlowersListContract.Effect.Navigation.ToFlowerDetails -> {
                    // Navigate
                    // navController.navigate("TODO")
                }
            }
        })
}
