package org.cazait.cazait_android

import androidx.lifecycle.LiveData

class CafeInterestRepository(private val cafeInterestDao: CafeInterestDao) {
    val getAll: LiveData<List<CafeInterestEntity>> = cafeInterestDao.getAll()

    suspend fun insertInterest(cafeInterestEntity: CafeInterestEntity) {
        cafeInterestDao.insertInterest(cafeInterestEntity)
    }
    suspend fun deleteInterest(cafeInterestEntity: CafeInterestEntity) {
        cafeInterestDao.deleteInterest(cafeInterestEntity)
    }

    suspend fun updateInterest(cafeInterestEntity: CafeInterestEntity) {
        cafeInterestDao.updateInterest(cafeInterestEntity)
    }
}