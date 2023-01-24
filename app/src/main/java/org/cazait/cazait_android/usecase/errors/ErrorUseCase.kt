package org.cazait.cazait_android.usecase.errors

import org.cazait.cazait_android.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}