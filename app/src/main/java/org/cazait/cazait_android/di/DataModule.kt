package org.cazait.cazait_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cazait.cazait_android.data.repository.DataRepositoryImpl
import org.cazait.cazait_android.data.repository.DataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository
}