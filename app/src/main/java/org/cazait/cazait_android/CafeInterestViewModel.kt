package org.cazait.cazait_android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CafeInterestViewModel(application: Application) : AndroidViewModel(application) {
    private val getall: LiveData<List<CafeInterestEntity>>
    private val repository: CafeInterestRepository

    init {
        val cafeInterestDao = CafeInterestDatabase.getDatabase(application).cafeInterestDao()
        repository = CafeInterestRepository(cafeInterestDao)
        getall = repository.getAll
    }

    fun insertInterest(cafeInterestEntity: CafeInterestEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertInterest(cafeInterestEntity)
        }
        fun deleteInterest(cafeInterestEntity: CafeInterestEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteInterest(cafeInterestEntity)
            }

            fun updateInterest(cafeInterestEntity: CafeInterestEntity) {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.updateInterest(cafeInterestEntity)
                }

            }
        }
    }
}