package org.cazait.cazait_android.usecase.errors

import org.cazait.cazait_android.data.error.Error
import org.cazait.cazait_android.data.error.mapper.ErrorMapper
import javax.inject.Inject

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}