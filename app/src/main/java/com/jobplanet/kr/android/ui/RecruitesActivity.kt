package com.jobplanet.kr.android.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.jobplanet.kr.android.ext.checkSearchWord
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.databinding.ActivityRecruitsBinding
import com.jobplanet.kr.android.ui.adapter.CategoryAdapter
import com.jobplanet.kr.android.ui.adapter.CompanyAdapter
import com.jobplanet.kr.android.util.BackButtonCloseHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// TODO:
//  0. 카테고리 중복선택시 체크 해제되는 문제
//  1. buildGradle의 import 라이브러리 신기술 찾아보고 적용
//  3. 테스트 코드 작성
//  4. ItemDecoration재설계
//  5. viewModel내 conver로직 부모로 뺄 것
//  6. ResponseType의 depth가 너무 깊다. 더 직관적인 처리 방법이 없을까?

@AndroidEntryPoint
class RecruitesActivity: BaseActivity<ActivityRecruitsBinding>(R.layout.activity_recruits) {

    private val backButtonCloseHandler by lazy { BackButtonCloseHandler(this) }

    private val categoryAdapter by lazy { CategoryAdapter() }
    private val companyAdapter by lazy { CompanyAdapter() }

    private val categoryViewModel: CategoryViewModel by viewModels()
    private val companyViewModel: CompanyViewModel by viewModels()

    private var selectedFilter = ""
    private var searchWord = ""

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.tvSearchCategory -> {
                with (view.tag as Pair<Int, String>) {
                    if (selectedFilter == second) return@OnClickListener

                    categoryAdapter.selectFilter(first)
                    selectedFilter = second
                }
                searchCompanies()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            categoryVm = categoryViewModel
            companyVm = companyViewModel

            layoutCommonSearch.etSearch.apply {
                checkSearchWord { searchWord ->
                    this@RecruitesActivity.searchWord = "$searchWord"
                    searchCompanies()
                }
            }

            rvCategory.apply {
                setHasFixedSize(true)
                adapter = categoryAdapter
                addItemDecoration(
                    CommonItemDecoration(
                        firstItemMargin = 20,
                        lastItemMargin = 20,
                        left = 4,
                        right = 4)
                )
            }

            rvCompany.apply {
                setHasFixedSize(true)
                adapter = companyAdapter
                addItemDecoration(
                    CommonGridItemDecoration(
                        outerMargin = 20,
                        innerMargin = 6,
                        firstTopLength = 8,
                        top = 20)
                )
            }
        }

        lifecycleScope.launch {
            whenCreated {
                categoryViewModel.clickListener = clickListener
                companyViewModel.clickListener = clickListener

                categoryViewModel.getSearchCategorys()
                companyViewModel.getCompanies()
            }
        }
    }

    override fun onBackPressed() {
        backButtonCloseHandler.appExit()
    }

    private fun searchCompanies() {
        companyAdapter.searchCompanies(
            filterWord = selectedFilter.ifEmpty { categoryViewModel.categoryResponse.value?.get(0)?.title ?: "" },
            searchWord = searchWord)
    }
}