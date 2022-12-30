package com.jobplanet.kr.android.api

import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.model.response.SampleResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {

    @GET("search")
    suspend fun getSearchCategory(): SearchCategoryResponse?

    @GET("test_data_recruit_items.json")
    suspend fun getCompanies(): RecrutesResponse

}