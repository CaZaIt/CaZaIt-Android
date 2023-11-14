package org.cazait.data.model.remote.datasource

import org.cazait.data.Resource
import org.cazait.data.model.remote.request.ReviewEditRequest
import org.cazait.data.model.remote.request.ReviewRequest
import org.cazait.data.model.remote.response.MenuResponse
import org.cazait.data.model.remote.response.ReviewEditResponse
import org.cazait.data.model.remote.response.ReviewResponse

interface CafeInfoRemoteDataSource {
    fun getMenus(cafeId: Long): Resource<MenuResponse>
    fun getReviews(cafeId: Long, body: ReviewRequest): Resource<ReviewResponse>
    fun postReview(
        userId: Long,
        cafeId: Long,
        body: ReviewEditRequest,
    ): Resource<ReviewEditResponse>
}
