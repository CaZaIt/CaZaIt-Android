package org.cazait.ui.base

import androidx.lifecycle.ViewModel
import org.cazait.errors.ErrorManager
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager
}
