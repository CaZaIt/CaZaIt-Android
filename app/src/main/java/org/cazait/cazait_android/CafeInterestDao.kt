package org.cazait.cazait_android

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CafeInterestDao {
    @Query("SELECT * FROM interest")
    fun getAll() : LiveData<List<CafeInterestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertInterest(cafeInterestEntity: CafeInterestEntity)

@Update
   suspend fun updateInterest(cafeInterestEntity: CafeInterestEntity)


   @Delete
    suspend fun deleteInterest(cafeInterestEntity: CafeInterestEntity)
}
