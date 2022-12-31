package com.jobplanet.kr.android.repository

import com.jobplanet.kr.android.api.BaseApi
import com.jobplanet.kr.android.model.response.RecrutesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecruteRepositoryImp @Inject constructor(
    private val baseApi: BaseApi
): RecruteRepository {

    override fun getCompanies(): Flow<RecrutesResponse> {
        return flow {
            val data = baseApi.getRecrutes()
            emit(data)
        }
    }
}