package org.cazait.ui.viewmodel.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun ViewModel.onMain(
    crossinline body: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch {
    body(this)
}

inline fun ViewModel.onIO(
    crossinline body: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch(Dispatchers.IO) {
    body(this)
}

inline fun ViewModel.onDefault(
    crossinline body: suspend CoroutineScope.() -> Unit,
) = viewModelScope.launch(Dispatchers.Default) {
    body(this)
}
