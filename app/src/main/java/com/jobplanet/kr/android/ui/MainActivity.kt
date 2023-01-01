package com.jobplanet.kr.android.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.jobplanet.kr.android.ext.checkSearchWord
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.ActivityMainBinding
import com.jobplanet.kr.android.ui.adapter.CategoryAdapter
import com.jobplanet.kr.android.ui.sub.CompanyFragment
import com.jobplanet.kr.android.ui.sub.CompanyViewModel
import com.jobplanet.kr.android.ui.sub.RecruteFragment
import com.jobplanet.kr.android.ui.sub.RecruteViewModel
import com.jobplanet.kr.android.util.BackButtonCloseHandler
import com.jobplanet.kr.android.util.CommonItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// TODO:
//  0. 리사이클러뷰 공통 사용 고민
//  1. buildGradle의 import 라이브러리 신기술 찾아보고 적용
//  3. 테스트 코드 작성
//  4. ItemDecoration재설계
//  5. viewModel내 conver로직 부모로 뺄 것
//  6. ResponseType의 depth가 너무 깊다. 더 직관적인 처리 방법이 없을까?
//  7. 시간나면 viewDeviderLine공통으로 빼기
//  8. border는 어떻게 공통화하면 좋을지?


@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val backButtonCloseHandler by lazy { BackButtonCloseHandler(this) }

    private val categoryAdapter by lazy { CategoryAdapter() }

    private val categoryViewModel: CategoryViewModel by viewModels()
    private val companyViewModel: CompanyViewModel by viewModels()
    private val recruteViewModel: RecruteViewModel by viewModels()

    private val recruteFragment = RecruteFragment()
    private val companyFragment = CompanyFragment()

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

                addFragment(type = selectedFilter)

                recruteFragment.recruteCommonAdapter.searchCompanies(
                    searchWord = searchWord,
                    filterWord = selectedFilter
                )
                companyFragment.companyAdapter.searchCompanies(
                    searchWord = searchWord,
                    filterWord = selectedFilter
                )
            }
        }
    }

    private fun addFragment(type: String) {
        supportFragmentManager.beginTransaction().apply {
            when (type) {
                SearchFilterType.RECRUTE.value -> {
                    replace(binding.flContainer.id, recruteFragment)
                }
                SearchFilterType.COMPANY.value -> {
                    replace(binding.flContainer.id, companyFragment)
                }
            }
        }.addToBackStack(type).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(type = SearchFilterType.RECRUTE.value)

        binding {
            categoryVm = categoryViewModel

            layoutCommonSearch.etSearch.apply {
                checkSearchWord { searchWord ->
                    this@MainActivity.searchWord = "$searchWord"

                    recruteFragment.arguments = Bundle().apply {
                        putString(RecruteFragment.SEARCH_WORD, "$searchWord")
                    }
                    companyFragment.arguments = Bundle().apply {
                        putString(CompanyFragment.SEARCH_WORD, "$searchWord")
                    }
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
        }

        lifecycleScope.launch {
            whenCreated {
                categoryViewModel.clickListener = clickListener
                categoryViewModel.getSearchCategorys()
            }
            recruteFragment.whenCreated {
                recruteViewModel.getRecrutes()
            }
            companyFragment.whenCreated {
                companyViewModel.getCompanies()
            }
        }
    }

    override fun onBackPressed() {
        backButtonCloseHandler.appExit()
    }
}