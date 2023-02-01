package org.cazait.cazait_android.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.repository.DataRepositorySource
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CafeInfoImgViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {
}