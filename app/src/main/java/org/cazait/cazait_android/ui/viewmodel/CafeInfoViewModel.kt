package org.cazait.cazait_android.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.repository.DataRepositorySource
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

<<<<<<< HEAD
open class CafeInfoViewModel : BaseViewModel() {
=======
@HiltViewModel
open class CafeInfoViewModel @Inject constructor(private val dataRepository: DataRepositorySource) : BaseViewModel() {


>>>>>>> 8c42482 ([Feat] #33 - LiveData + DataBinding + RecyclerView)
}