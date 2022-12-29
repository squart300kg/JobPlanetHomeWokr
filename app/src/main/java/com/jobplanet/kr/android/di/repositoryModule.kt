package com.jobplanet.kr.android.di

import com.jobplanet.kr.android.repository.ExampleRepository
import com.jobplanet.kr.android.repository.ExampleRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    factory <ExampleRepository> { ExampleRepositoryImp(get()) }
}