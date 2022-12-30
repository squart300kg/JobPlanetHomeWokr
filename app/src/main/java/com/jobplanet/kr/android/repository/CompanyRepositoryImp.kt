package com.jobplanet.kr.android.repository

import com.jobplanet.kr.android.api.BaseApi
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompanyRepositoryImp @Inject constructor(
    private val baseApi: BaseApi
): CompanyRepository {

    override fun getCompanies(): Flow<RecrutesResponse> {
        return flow {
            val data = baseApi.getCompanies()
            emit(data)
        }
    }
}