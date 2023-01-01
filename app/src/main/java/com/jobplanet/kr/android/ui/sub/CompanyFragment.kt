package com.jobplanet.kr.android.ui.sub

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseFragment
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.FragmentCompanyBinding
import com.jobplanet.kr.android.ui.adapter.CompanyAdapter
import com.jobplanet.kr.android.util.CommonItemDecoration

class CompanyFragment: BaseFragment<FragmentCompanyBinding>(R.layout.fragment_company) {

    companion object {
        val SEARCH_WORD = "searchWord"
    }

    private val companyAdapter by lazy { CompanyAdapter() }

    private val companyViewModel: CompanyViewModel by activityViewModels()

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
                        orientation = LinearLayout.VERTICAL
                    )
                )
            }
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
//        args?.let { bundle ->
//            bundle.getString(RecruteFragment.SEARCH_WORD)?.let { searchWord ->
//                recruteCommonAdapter.searchCompanies(
//                    filterWord = SearchFilterType.COMPANY.value,
//                    searchWord = searchWord)
//            }
//        }
    }
}