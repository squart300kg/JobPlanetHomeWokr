package com.jobplanet.kr.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.databinding.ActivityRecruitsBinding
import com.jobplanet.kr.android.ui.adapter.CategoryAdapter
import com.jobplanet.kr.android.util.BackButtonCloseHandler
import dagger.hilt.android.AndroidEntryPoint

// TODO:
//  1. buildGradle의 import 라이브러리 신기술 찾아보고 적용
//  2. KOIN -> HILT전환
//  3. 테스트 코드 작성
//  4. ItemDecoration재설계

@AndroidEntryPoint
class RecruitesActivity: BaseActivity<ActivityRecruitsBinding>(R.layout.activity_recruits) {

    private val backButtonCloseHandler by lazy { BackButtonCloseHandler(this) }

    private val categoryViewModel: CategoryViewModel by viewModels()
    private val companyViewModel: CompanyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            categoryVm = categoryViewModel
            companyVm = companyViewModel

            rvCategory.apply {
                setHasFixedSize(true)
                adapter = CategoryAdapter()
                addItemDecoration(
                    CommonItemDecoration(
                        firstItemMargin = 20,
                        lastItemMargin = 20,
                        left = 4,
                        right = 4
                    )
                )

            }

            rvCompany.apply {
                setHasFixedSize(true)
//                adapter = CompanyAdapter()
//                addItemDecoration(
//                    CommonGridItemDecoration(
//                        outerMargin = 20,
//                        innerMargin = 6,
//                        firstTopLength = 8,
//                        top = 20
//
//                    )
//                )

            }

        }
        categoryViewModel.getSearchCategorys()
        companyViewModel.getCompanies()
    }

    override fun onBackPressed() {
        backButtonCloseHandler.appExit()
    }
}