package org.cazait.cazait_android.data.model.remote.datasource

import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.ReviewEditRequest
import org.cazait.cazait_android.data.model.remote.request.ReviewRequest
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.data.model.remote.response.ReviewEditResponse
import org.cazait.cazait_android.data.model.remote.response.ReviewResponse

interface CafeInfoRemoteDataSource {
    fun getMenus(cafeId: Long): Resource<MenuResponse>
    fun getReviews(cafeId: Long, body: ReviewRequest): Resource<ReviewResponse>
    fun postReview(userId:Long, cafeId: Long, body: ReviewEditRequest): Resource<ReviewEditResponse>
}