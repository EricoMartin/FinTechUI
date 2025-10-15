package com.basebox.fintechui.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.basebox.fintechui.ui.components.ActionButtons
import com.basebox.fintechui.ui.components.AssetsSection
import com.basebox.fintechui.ui.components.BalanceCard
import com.basebox.fintechui.ui.components.TopSection
import com.basebox.fintechui.ui.components.TransactionHeader
import com.basebox.fintechui.ui.components.TransactionItem
import com.basebox.fintechui.ui.components.WalletBottomNavBar
import com.basebox.fintechui.ui.viewmodel.WalletViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletDashboardScreen(viewModel: WalletViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
    val transactions by viewModel.transactions.collectAsState()
    val assets by viewModel.assets.collectAsState()


    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { WalletBottomNavBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            TopSection(
                modifier = Modifier.padding( vertical = 16.dp),
                userName = viewModel.userName
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),

                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    BalanceCard(
                        balanceUiStateFlow = viewModel.balanceUiState,
                        onToggle = { viewModel.toggleBalance() })
                }
                item { ActionButtons(onActionClick = { action -> viewModel.onAction(action) }) }
                item { TransactionHeader("My Assets") }
                item { AssetsSection(assets = assets) }
                item { TransactionHeader("Transactions") }
                items(transactions) { transaction -> TransactionItem(transaction = transaction) }
            }
        }
    }
}