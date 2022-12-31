package com.jobplanet.kr.android.repository


import com.jobplanet.kr.android.model.response.RecrutesResponse
import kotlinx.coroutines.flow.Flow

interface RecruteRepository {

    fun getCompanies(): Flow<RecrutesResponse>
}