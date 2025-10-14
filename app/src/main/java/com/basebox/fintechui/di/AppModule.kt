package com.basebox.fintechui.di


import com.basebox.fintechui.data.repository.FakeWalletRepository
import com.basebox.fintechui.data.repository.WalletRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindWalletRepository(repo: FakeWalletRepository): WalletRepository
}