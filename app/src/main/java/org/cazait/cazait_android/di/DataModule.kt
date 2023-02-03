package org.cazait.cazait_android.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cazait.cazait_android.data.repository.DataRepositoryImpl
import org.cazait.cazait_android.data.repository.DataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    private lateinit var repository: DataRepository
    @Provides
    fun provideDataRepository(): DataRepository {
        return repository
    }

    @Inject
    fun provisionDataRepository(repository: DataRepository) {
        this.repository = repository
    }
}