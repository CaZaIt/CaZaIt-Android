package org.cazait.data.model.remote.datasource

import org.cazait.data.Resource
import org.cazait.data.api.CafeService
import org.cazait.data.error.NO_INTERNET_CONNECTION
import org.cazait.data.model.remote.ServiceGenerator
import org.cazait.data.model.remote.request.ReviewEditRequest
import org.cazait.data.model.remote.request.ReviewRequest
import org.cazait.data.model.remote.response.MenuResponse
import org.cazait.data.model.remote.response.ReviewEditResponse
import org.cazait.data.model.remote.response.ReviewResponse
import org.cazait.errors.ErrorManager
import org.cazait.network.NetworkConnectivity
import java.io.IOException
import javax.inject.Inject

class CafeInfoRemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity,
    private val errorManager: ErrorManager,
) : CafeInfoRemoteDataSource {
    private val cafeService = serviceGenerator.createService(CafeService::class.java)

    override fun getMenus(cafeId: Long): Resource<MenuResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = cafeService.getMenus(cafeId = cafeId).execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun getReviews(cafeId: Long, query: ReviewRequest): Resource<ReviewResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = cafeService.getReviews(cafeId = cafeId, sortBy = query.sortBy).execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun postReview(
        userId: Long,
        cafeId: Long,
        body: ReviewEditRequest,
    ): Resource<ReviewEditResponse> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response =
                cafeService.postReview(userId = userId, cafeId = cafeId, reviewEditRequest = body)
                    .execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message(), null)
            }
        } catch (e: IOException) {
            Resource.Error(e.message, null)
        }
    }
}
