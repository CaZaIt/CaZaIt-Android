package org.cazait.cazait_android.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cazait.cazait_android.data.error.mapper.ErrorMapper
import org.cazait.cazait_android.data.error.mapper.ErrorMapperSource
import org.cazait.cazait_android.usecase.errors.ErrorManager
import org.cazait.cazait_android.usecase.errors.ErrorUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}