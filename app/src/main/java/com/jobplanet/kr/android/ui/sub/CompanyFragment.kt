package com.jobplanet.kr.android.ui.sub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseFragment
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.FragmentCompanyBinding
import com.jobplanet.kr.android.ui.CompanyDetailActivity
import com.jobplanet.kr.android.ui.adapter.CompanyAdapter
import com.jobplanet.kr.android.util.CommonItemDecoration

class CompanyFragment: BaseFragment<FragmentCompanyBinding>(R.layout.fragment_company) {

    companion object {
        val SEARCH_WORD = "searchWord"
    }

    val companyAdapter by lazy { CompanyAdapter() }

    private val companyViewModel: CompanyViewModel by activityViewModels()

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.companyRootView -> {
                /**
                 * 상세화면 구현 로직입니다.
                 * 해당 부분은 안내의 허락에 따라 임의로 개발하였음을 말씀드립니다.
                 */
                Intent(requireActivity(), CompanyDetailActivity::class.java).apply {
                    putExtra(CompanyDetailActivity.THUMBNAIL, companyViewModel.companyResponse.value?.get(view.tag as Int)?.logoPath ?: "")
                    putExtra(CompanyDetailActivity.TITLE, companyViewModel.companyResponse.value?.get(view.tag as Int)?.name ?: "")
                    putExtra(CompanyDetailActivity.RATING, companyViewModel.companyResponse.value?.get(view.tag as Int)?.rateTotalAvg.toString())
                    startActivity(this)
                }
            }
            R.id.recruteRootView -> {
                val tag = view.tag as IntArray
                Intent(requireActivity(), CompanyDetailActivity::class.java).apply {
                    putExtra(CompanyDetailActivity.THUMBNAIL, companyViewModel.companyResponse.value?.get(tag[0])?.recommendRecruit?.get(tag[1])?.imageUrl ?: "")
                    putExtra(CompanyDetailActivity.COMPANY, companyViewModel.companyResponse.value?.get(tag[0])?.recommendRecruit?.get(tag[1])?.company?.name ?: "")
                    putExtra(CompanyDetailActivity.TITLE, companyViewModel.companyResponse.value?.get(tag[0])?.recommendRecruit?.get(tag[1])?.title ?: "")
                    putExtra(CompanyDetailActivity.RATING, companyViewModel.companyResponse.value?.get(tag[0])?.recommendRecruit?.get(tag[1])?.company?.ratings?.map { it.rating }?.max().toString())
                    putExtra(CompanyDetailActivity.REWARD, companyViewModel.companyResponse.value?.get(tag[0])?.recommendRecruit?.get(tag[1])?.reward.toString())
                    putExtra(CompanyDetailActivity.APPEAL, companyViewModel.companyResponse.value?.get(tag[0])?.recommendRecruit?.get(tag[1])?.appeal ?: "")
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        companyViewModel.clickListener = clickListener
        companyViewModel.getCompanies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            companyVm = companyViewModel

            rvCompany.apply {
                setHasFixedSize(true)
                adapter = companyAdapter
                addItemDecoration(
                    CommonItemDecoration(
                        top = 24,
                        orientation = LinearLayout.VERTICAL)
                )
            }
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args?.let { bundle ->
            bundle.getString(RecruteFragment.SEARCH_WORD)?.let { searchWord ->
                companyAdapter.searchCompanies(
                    filterWord = SearchFilterType.COMPANY.value,
                    searchWord = searchWord)
            }
        }
    }
}