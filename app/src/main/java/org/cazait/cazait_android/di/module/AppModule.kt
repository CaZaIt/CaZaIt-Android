package org.cazait.cazait_android.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import org.cazait.cazait_android.data.model.domain.local.LocalData
import org.cazait.cazait_android.network.Network
import org.cazait.cazait_android.network.NetworkConnectivity

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context): LocalData {
        return LocalData(context)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }
}