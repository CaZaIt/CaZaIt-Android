package org.cazait.cazait_android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.Datasource
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.data.repository.DataRepositorySource
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
open class CafeListViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {

    private val list = arrayListOf<CafeState>()
    private val _cafeStateList = MutableLiveData<ArrayList<CafeState>>()
    val cafeStateList: LiveData<ArrayList<CafeState>>
        get() = _cafeStateList

    init {
        val dataset: ArrayList<CafeState> = Datasource().loadAffirmations()
        getCafeStateList(dataset)
    }

    // For Test
    fun addItem() {
        val state = CafeState("1", "추가된카페","100", "광진구","혼잡")
        list.add(state)
        _cafeStateList.postValue(list)
        Log.d("te","te")
    }

    private fun getCafeStateList(dataset: ArrayList<CafeState>) {
        list.addAll(dataset)
        _cafeStateList.postValue(dataset)
    }
}