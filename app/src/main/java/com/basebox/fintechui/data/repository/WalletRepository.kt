package com.basebox.fintechui.data.repository


import com.basebox.fintechui.data.model.Asset
import com.basebox.fintechui.data.model.Transaction


interface WalletRepository {
    suspend fun getAssets(): List<Asset>
    suspend fun getTransactions(): List<Transaction>
}