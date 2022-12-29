package com.jobplanet.kr.android.repository

import com.jobplanet.kr.android.api.SampleApi
import com.jobplanet.kr.android.model.response.SampleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by sangyoon on 2021/07/27
 */
class ExampleRepositoryImp(
    private val youtubeApi: SampleApi
): ExampleRepository {

    override fun exampleFun(playListId: String, apiKey: String): Flow<SampleResponse> {
        return flow {
            val data = youtubeApi.getFreeLectures(
                playListId = playListId,
                key = apiKey)
            emit(data)
        }
    }

}