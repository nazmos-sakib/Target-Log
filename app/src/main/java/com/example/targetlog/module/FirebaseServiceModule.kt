package com.example.targetlog.module

import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import com.example.targetlog.data.db.firebase.repository.AccountServiceImpl
import com.example.targetlog.data.db.firebase.repository.FireStoreServiceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService


    @Binds abstract fun provideFireStoreService(impl: FireStoreServiceImp): FireStoreService

    //@Binds abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}