package com.jobplanet.kr.android.repository

import com.jobplanet.kr.android.api.BaseApi
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *카테고리 또한 서버로부터 받아올 가능성이 있기에 추가 설계했습니다.
 */
class CategoryRepositoryImp @Inject constructor(
    private val baseApi: BaseApi
): CategoryRepository {

    private val searchCategoryResponse = SearchCategoryResponse(
        item = listOf(
            SearchCategoryResponse.SearchCategory("채용"),
            SearchCategoryResponse.SearchCategory("기업"))
    )

    override fun getCategories(): Flow<SearchCategoryResponse> {
        return flow {
            emit(searchCategoryResponse)
        }
    }
}