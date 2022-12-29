package com.jobplanet.kr.android.repository


import com.jobplanet.kr.android.model.response.SampleResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by sangyoon on 2021/07/27
 */
interface ExampleRepository {

    fun exampleFun(playListId: String, apiKey: String): Flow<SampleResponse>

}