package com.vapetrosyan.flowrspot.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vapetrosyan.flowrspot.ui.feature.flower_list.FlowersListDestination
import com.vapetrosyan.flowrspot.ui.theme.FlowrSpotAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowrSpotAppTheme {
                FlowSpotApp()
            }
        }
    }
}

@Composable
private fun FlowSpotApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.FLOWERS_LIST) {
        composable(route = NavigationKeys.Route.FLOWERS_LIST) {
            FlowersListDestination(navController)
        }
    }
}

object NavigationKeys {
    object Route {
        const val FLOWERS_LIST = "flowers_list"
    }
}