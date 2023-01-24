package org.cazait.cazait_android.data.model.domain.local

import android.content.Context
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.dto.login.LoginRequest
import org.cazait.cazait_android.data.dto.login.LoginResponse
import javax.inject.Inject

class LocalData @Inject constructor(val context: Context) {

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        /*
        1. 로그인 요청하기 이 함수는 RemoteData 옮겨야 할듯함
        2. 요청 결과가 성공일 경우 return Resource.Success(LoginResponse(변수들))
        3. 요청 경과가 실패일 경우 return Resource.DataError(PASSWORD_ERROR)
         */
        TODO()
    }
}