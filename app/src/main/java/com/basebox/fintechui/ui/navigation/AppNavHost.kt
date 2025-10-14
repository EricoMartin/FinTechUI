package com.basebox.fintechui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basebox.fintechui.ui.dashboard.WalletDashboardScreen
import com.basebox.fintechui.ui.viewmodel.WalletViewModel


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            val vm: WalletViewModel = hiltViewModel()
            WalletDashboardScreen(viewModel = vm)
        }
    }
}