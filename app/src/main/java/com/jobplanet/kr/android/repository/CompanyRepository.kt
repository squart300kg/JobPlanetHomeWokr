package com.jobplanet.kr.android.repository


import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {

    fun getCompanies(): Flow<RecrutesResponse>
}