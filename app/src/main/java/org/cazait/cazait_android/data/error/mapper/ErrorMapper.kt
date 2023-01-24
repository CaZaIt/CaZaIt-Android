package org.cazait.cazait_android.data.error.mapper

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.error.NETWORK_ERROR
import org.cazait.cazait_android.data.error.NO_INTERNET_CONNECTION
import org.cazait.cazait_android.data.error.PASSWORD_ERROR
import org.cazait.cazait_android.data.error.USER_NAME_ERROR
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) : ErrorMapperSource {
    override val errorsMap: Map<Int, String>
        get() = mapOf(
            NO_INTERNET_CONNECTION to getErrorString(R.string.no_internet),
            NETWORK_ERROR to getErrorString(R.string.network_error),
            PASSWORD_ERROR to getErrorString(R.string.invalid_password),
            USER_NAME_ERROR to getErrorString(R.string.invalid_user_name),
        ).withDefault { getErrorString(R.string.network_error) }

    override fun getErrorString(errorId: Int): String = context.getString(errorId)
}