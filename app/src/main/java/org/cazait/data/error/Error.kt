package org.cazait.data.error

class Error(val code: Int, val description: String) {
    constructor(exception: Exception) : this(
        code = DEFAULT_ERROR,
        description = exception.message ?: "",
    )
}

const val DEFAULT_ERROR = -1
const val NO_INTERNET_CONNECTION = -2
const val NETWORK_ERROR = -3
const val PASSWORD_ERROR = -101
const val EMAIL_ERROR = -102
const val EMAIL_OR_PASSWORD_ERROR = -103
const val EXPIRED_ACCESS_TOKEN = -104
const val EXPIRED_REFRESH_TOKEN = -105
const val INVALID_ACCESS_TOKEN = -106
