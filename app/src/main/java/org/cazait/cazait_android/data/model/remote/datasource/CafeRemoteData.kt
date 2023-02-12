package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.api.CafeService
import org.cazait.cazait_android.data.error.NETWORK_ERROR
import org.cazait.cazait_android.data.error.NO_INTERNET_CONNECTION
import org.cazait.cazait_android.data.model.remote.ServiceGenerator
import org.cazait.cazait_android.data.model.remote.request.CafeListRequest
import org.cazait.cazait_android.data.model.remote.response.CafeListResponse
import org.cazait.cazait_android.data.model.remote.response.DeleteInterestCafeResponse
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
import org.cazait.cazait_android.data.model.remote.response.PostInterestCafeResponse
import org.cazait.cazait_android.network.NetworkConnectivity
import org.cazait.cazait_android.usecase.errors.ErrorManager
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CafeRemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity,
    private val errorManager: ErrorManager
) : CafeRemoteDataSource {
    private val cafeService = serviceGenerator.createService(CafeService::class.java)

    override fun getCafeList(userId: Long, query: CafeListRequest): Resource<CafeListResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = cafeService.getCafes(
                userId = userId,
                latitude = query.latitude,
                longitude = query.longitude,
                limit = query.limit,
                sort = query.sort
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

    override fun getInterestCafes(userId: Long): Resource<InterestCafesResponse> {
        if(!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = cafeService.getInterestCafes(userId).execute()

            if(response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun postInterestCafe(cafeId: Long, userId: Long): Resource<PostInterestCafeResponse> {
        if(!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = cafeService.postInterestCafe(userId = userId, cafeId = cafeId).execute()

            if(response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun deleteInterestCafe(favoritesId: Long): Resource<DeleteInterestCafeResponse> {
        if(!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = cafeService.deleteInterestCafe(favoritesId).execute()

            if(response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if(!networkConnectivity.isConnected())
            return NO_INTERNET_CONNECTION

        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if(response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}