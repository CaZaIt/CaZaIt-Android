package org.cazait.errors

import org.cazait.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
