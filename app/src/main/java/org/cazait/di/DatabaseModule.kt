package org.cazait.di

// import android.content.Context
// import androidx.room.Room
// import dagger.Module
// import dagger.Provides
// import dagger.hilt.InstallIn
// import dagger.hilt.android.qualifiers.ApplicationContext
// import dagger.hilt.components.SingletonComponent
// import org.cazait.cazait_android.data.model.local.AppDatabase
// import org.cazait.cazait_android.data.model.local.LogDao
// import javax.inject.Singleton

// @Module
// @InstallIn(SingletonComponent::class)
// object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "logging.db"
//        ).build()
//    }
//
//    @Provides
//    fun provideLogDao(database: AppDatabase): LogDao {
//        return database.logDao()
//    }
// }
