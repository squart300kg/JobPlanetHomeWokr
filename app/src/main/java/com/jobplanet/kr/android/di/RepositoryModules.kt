package com.jobplanet.kr.android.di

import com.jobplanet.kr.android.api.BaseApi
import com.jobplanet.kr.android.repository.CategoryRepository
import com.jobplanet.kr.android.repository.CategoryRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModules {

    @Singleton
    @Provides
    fun provideCategoryRepository(baseApi: BaseApi): CategoryRepository = CategoryRepositoryImp(baseApi)

}