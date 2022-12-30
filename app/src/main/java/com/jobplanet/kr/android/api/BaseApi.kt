package com.jobplanet.kr.android.api

import com.jobplanet.kr.android.model.response.SampleResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {

    @GET("playlistItems")
    suspend fun getFreeLectures(
        @Query("part")part: String = "snippet",
        @Query("fields")fields: String = "items(snippet(title, description, resourceId.videoId, thumbnails.high.url))",
        @Query("key")key: String,
        @Query("playlistId")playListId: String,
        @Query("maxResults")maxResults: String = "15",
    ): SampleResponse

    @GET("search")
    suspend fun getSearchCategory(): SearchCategoryResponse?

}