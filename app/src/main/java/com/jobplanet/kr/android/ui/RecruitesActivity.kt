package com.jobplanet.kr.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.databinding.ActivityRecruitsBinding
import com.jobplanet.kr.android.util.BackButtonCloseHandler
import dagger.hilt.android.AndroidEntryPoint

// TODO:
//  1. buildGradle의 import 라이브러리 신기술 찾아보고 적용
//  2. KOIN -> HILT전환
//  3. 테스트 코드 작성

@AndroidEntryPoint
class RecruitesActivity: BaseActivity<ActivityRecruitsBinding>(R.layout.activity_recruits) {

    private val backButtonCloseHandler by lazy { BackButtonCloseHandler(this) }

    private val searchViewModel: SearchViewModel by viewModels()
    private val companyViewModel: CompanyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            companyVm = companyViewModel

//            rvCategory.apply {
//                setHasFixedSize(true)
//                addItemDecoration(
//                    CommonItemDecoration(
//                        left = 6,
//                        right = 6
//                    )
//                )
//            }
            searchViewModel.test()
            companyViewModel.test()
        }
    }

    override fun onBackPressed() {
        backButtonCloseHandler.appExit()
    }
}