package org.cazait.cazait_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.CafeMenu
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.data.repository.DataRepositoryImpl
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
open class CafeInfoMenuViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    private val list = arrayListOf<CafeMenu>()
    private val _cafeMenuList = MutableLiveData<Resource<MenuResponse>>()
    val cafeMenuList: LiveData<Resource<MenuResponse>>
        get() = _cafeMenuList

    init {
        initDataSet()
    }

    private fun initDataSet() {

        // 테스트용으로 값을 넣어주고 있음
        val dataList = arrayListOf(
            CafeMenu(
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노",
                "아메리카노",
                1500
            )
        )

    }

    fun getMenus(cafeId: Long) {
        viewModelScope.launch {
            _cafeMenuList.value = Resource.Loading()
            dataRepository.getMenus(cafeId).collect {
                _cafeMenuList.value = it
            }
        }
    }
}