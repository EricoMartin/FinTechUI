package com.basebox.fintechui.data.repository


import com.basebox.fintechui.R
import com.basebox.fintechui.data.model.Asset
import com.basebox.fintechui.data.model.Transaction
import javax.inject.Inject


class FakeWalletRepository @Inject constructor(): WalletRepository {
    override suspend fun getAssets(): List<Asset> = listOf(Asset("USDC", "$567.90", R.drawable.usdc), Asset("BTC", "$0.003146", R.drawable.btc), Asset("USDC", "$567.90", R.drawable.usdc),
                    Asset("BTC", "$0.003146", R.drawable.btc))
    override suspend fun getTransactions(): List<Transaction> = listOf(
        Transaction("Reward", "+149.87 USD", "Received", "Success", "15:22:55 • 2025-06-19"),
        Transaction("Deposit", "-289.99 USD", "Fail", "Failed", "08:12:49 • 2025-06-16"),
        Transaction("Credit", "+462.43 USD", "Received", "Success", "15:22:55 • 2025-06-19"),
    Transaction("Withdrawal", "-99.99 USD", "Received", "Success", "08:12:49 • 2025-06-16")

    )
}