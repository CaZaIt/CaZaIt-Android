package org.cazait.cazait_android

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CafeInterestEntity::class], version = 1)
abstract class CafeInterestDatabase : RoomDatabase() {
    abstract fun cafeInterestDao(): CafeInterestDao

    companion object {
        @Volatile
        private var INSTANCE: CafeInterestDatabase? = null

        fun getDatabase(context: Context): CafeInterestDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CafeInterestDatabase::class.java,
                        "cafe_interest"
                    ).build()
                }
                return instance
            }
        }
    }
}