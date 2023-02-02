package org.cazait.cazait_android.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

}