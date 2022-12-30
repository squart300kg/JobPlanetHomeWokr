package com.jobplanet.kr.android.repository


import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories(): Flow<SearchCategoryResponse>
}