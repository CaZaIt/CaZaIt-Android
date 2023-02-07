package org.cazait.cazait_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.data.repository.DataRepositoryImpl
import org.cazait.cazait_android.data.repository.UserRepository
import org.cazait.cazait_android.data.repository.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun provideDataRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository

    @Binds
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}