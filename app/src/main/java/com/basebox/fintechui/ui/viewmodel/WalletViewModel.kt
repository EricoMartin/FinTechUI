package com.basebox.fintechui.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basebox.fintechui.data.model.Asset
import com.basebox.fintechui.data.model.Transaction
import com.basebox.fintechui.data.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class BalanceUiState(val show: Boolean = false, val total: String = "$34,567.90", val totalChange: String = "$8,784.13 (8.78%)")


@HiltViewModel
class WalletViewModel @Inject constructor(private val repo: WalletRepository) : ViewModel() {
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets


    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions


    private val _balanceUi = MutableStateFlow(BalanceUiState())
    val balanceUiState: StateFlow<BalanceUiState> = _balanceUi


    val userName = "Dianne Russell"


    init { loadData() }


    private fun loadData() {
        viewModelScope.launch {
            _assets.value = repo.getAssets()
            _transactions.value = repo.getTransactions()
        }
    }


    fun toggleBalance() { _balanceUi.value = _balanceUi.value.copy(show = !_balanceUi.value.show) }


    fun onAction(action: String) { /* implement action handling, navigation or events */ }
}