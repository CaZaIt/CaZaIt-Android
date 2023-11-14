package org.cazait.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.data.repository.DataRepository
import org.cazait.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CafeInfoImgViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel()
