package org.cazait.cazait_android.ui.base

import androidx.lifecycle.ViewModel
import org.cazait.cazait_android.usecase.errors.ErrorManager
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager
}
