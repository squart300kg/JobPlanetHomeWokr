package com.jobplanet.kr.android.ui.sub

import android.content.Intent
import android.os.Bundle
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
                val index = view.tag as Int
                Intent(requireActivity(), CompanyDetailActivity::class.java).apply {
                    companyViewModel.getCellItemByIndex(index)?.also { cellItem ->
                        putExtra(CompanyDetailActivity.THUMBNAIL, cellItem.logoPath)
                        putExtra(CompanyDetailActivity.TITLE, cellItem.name ?: "")
                        putExtra(CompanyDetailActivity.RATING, cellItem.rateTotalAvg.toString())
                    }
                    startActivity(this)
                }
            }
            R.id.recruteRootView -> {
                /**
                 * 상세화면 구현 로직입니다.
                 * 해당 부분은 안내의 허락에 따라 임의로 개발하였음을 말씀드립니다.
                 */
                val rowIndex = (view.tag as IntArray)[0]
                val colIndex = (view.tag as IntArray)[1]
                Intent(requireActivity(), CompanyDetailActivity::class.java).apply {
                    companyViewModel.getRecruteItemByIndex(rowIndex, colIndex)?.also { recruteItem ->
                        putExtra(CompanyDetailActivity.THUMBNAIL, recruteItem.imageUrl)
                        putExtra(CompanyDetailActivity.COMPANY, recruteItem.company.name)
                        putExtra(CompanyDetailActivity.TITLE, recruteItem.title)
                        putExtra(CompanyDetailActivity.RATING, recruteItem.company.ratings.map { it.rating }.max().toString())
                        putExtra(CompanyDetailActivity.REWARD, recruteItem.reward.toString())
                        putExtra(CompanyDetailActivity.APPEAL, recruteItem.appeal)
                    }
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