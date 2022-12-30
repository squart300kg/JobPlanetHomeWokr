package com.jobplanet.kr.android.di

import android.util.Log
import com.jobplanet.kr.android.BuildConfig
import com.jobplanet.kr.android.api.BaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object ApiModules {

    /**
     * 정책에 따라 바뀔 수 있습니다. 우선 임의로 10초로 설정해 놓았습니다.
     */
    const val TIMEOUT = 10L

    @Provides
    fun debugInterceptor() = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("API", message)
        }

    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(debugInterceptor())
            .build()
    }

    @Provides
    fun provideCommonApi(
        okHttpClient: OkHttpClient
    ) : BaseApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BaseApi::class.java)
    }

}