package com.jobplanet.kr.android.ui.sub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseFragment
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.FragmentRecruteBinding
import com.jobplanet.kr.android.ui.CompanyDetailActivity
import com.jobplanet.kr.android.ui.adapter.RecruteCommonAdapter
import com.jobplanet.kr.android.util.CommonGridItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecruteFragment: BaseFragment<FragmentRecruteBinding>(R.layout.fragment_recrute) {

    companion object {
        val SEARCH_WORD = "searchWord"
    }

    val recruteCommonAdapter by lazy { RecruteCommonAdapter() }

    private val recruteViewModel: RecruteViewModel by activityViewModels()

    val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.recruteRootView -> {
                /**
                 * 상세화면 구현 로직입니다.
                 * 해당 부분은 안내의 허락에 따라 임의로 개발하였음을 말씀드립니다.
                 */
                val index = (view.tag as IntArray)[0]
                Intent(requireActivity(), CompanyDetailActivity::class.java).apply {
                    recruteViewModel.getRecruteItemByIndex(index)?.also { recruteItem ->
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycleScope.launch {
            whenCreated {
                recruteViewModel.clickListener = clickListener
                recruteViewModel.getRecrutes()
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            recruteVm = recruteViewModel

            rvRecrute.apply {
                setHasFixedSize(true)
                adapter = recruteCommonAdapter
                addItemDecoration(
                    CommonGridItemDecoration(
                        outerMargin = 20,
                        innerMargin = 6,
                        firstTopLength = 8,
                        lastBottomLength = 8,
                        top = 20)
                )
            }
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args?.let { bundle ->
            bundle.getString(SEARCH_WORD)?.let { searchWord ->
                recruteCommonAdapter.searchCompanies(
                    filterWord = SearchFilterType.RECRUTE.value,
                    searchWord = searchWord)
            }
        }
    }

}