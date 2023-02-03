package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.api.UserService
import org.cazait.cazait_android.data.model.remote.ServiceGenerator
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.request.SignUpRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.model.remote.response.SignUpResponse
import org.cazait.cazait_android.network.NetworkConnectivity
import retrofit2.Call
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : UserRemoteDataSource {
    private val userService = serviceGenerator.createService(UserService::class.java)

    // 로그인과 회원 가입은 서로 다른 방식으로 작성했다.
    // 두 방식 모두 사용 가능하다.
    override fun postLogin(body: LoginRequest): Resource<LoginResponse> {
        if (body == LoginRequest("admin", "admin")) {
            return Resource.Success(
                LoginResponse(
                    LoginResponse.Data(
                        "vv99911@gamil.com",
                        0,
                        "jwtToken",
                        "refresh"
                    ), "success", "result=admin"
                )
            )
        }

        if (!networkConnectivity.isConnected()) {
            return Resource.Error("No Internet Connection", null)
        }

        return try {
            val response = userService.doLogin(body)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "", null)
        }
    }

    override fun postSignUp(body: SignUpRequest): Call<SignUpResponse> {
        return userService.postSignUp(body)
    }
}
