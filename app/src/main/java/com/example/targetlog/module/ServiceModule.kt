package com.example.targetlog.module

import com.example.targetlog.model.service.AccountService
import com.example.targetlog.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    //@Binds abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}