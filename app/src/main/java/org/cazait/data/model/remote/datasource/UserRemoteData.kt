package org.cazait.data.model.remote.datasource

import org.cazait.data.Resource
import org.cazait.data.api.UserService
import org.cazait.data.error.NO_INTERNET_CONNECTION
import org.cazait.data.model.remote.ServiceGenerator
import org.cazait.data.model.remote.request.LoginRequest
import org.cazait.data.model.remote.request.SignUpRequest
import org.cazait.data.model.remote.response.LoginResponse
import org.cazait.data.model.remote.response.SignUpResponse
import org.cazait.data.model.remote.response.TokenResponse
import org.cazait.errors.ErrorManager
import org.cazait.network.NetworkConnectivity
import java.io.IOException
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity,
    private val errorManager: ErrorManager,
) : UserRemoteDataSource {
    private val userService = serviceGenerator.createService(UserService::class.java)

    override fun postLogin(body: LoginRequest): Resource<LoginResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = userService.postLogin(loginRequest = body).execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message(), null)
            }
        } catch (e: IOException) {
            Resource.Error(e.message, null)
        }
    }

    override fun postToken(
        userId: Long,
        accessTokenHeader: Map<String, String>,
        refreshTokenHeader: Map<String, String>,
    ): Resource<TokenResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = userService.postRefreshToken(
                userId = userId,
                accessToken = accessTokenHeader,
                refreshToken = refreshTokenHeader,
            ).execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun postSignUp(body: SignUpRequest): Resource<SignUpResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = userService.postSignUp(body).execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }
}
