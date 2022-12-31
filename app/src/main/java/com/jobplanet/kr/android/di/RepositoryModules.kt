package com.jobplanet.kr.android.di

import com.jobplanet.kr.android.api.BaseApi
import com.jobplanet.kr.android.repository.*
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

    @Singleton
    @Provides
    fun provideRecrutesRepository(baseApi: BaseApi): RecruteRepository = RecruteRepositoryImp(baseApi)

    @Singleton
    @Provides
    fun provideCompanyRepository(baseApi: BaseApi): CompanyRepository = CompanyRepositoryImp(baseApi)

}