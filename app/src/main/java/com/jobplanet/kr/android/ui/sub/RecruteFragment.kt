package com.jobplanet.kr.android.ui.sub

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseFragment
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.FragmentRecruteBinding
import com.jobplanet.kr.android.ui.adapter.RecruteAdapter
import com.jobplanet.kr.android.util.CommonGridItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecruteFragment: BaseFragment<FragmentRecruteBinding>(R.layout.fragment_recrute) {

    companion object {
        val SEARCH_WORK = "searchWord"
    }

    private val recruteAdapter by lazy { RecruteAdapter() }

    private val recruteViewModel: RecruteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            companyVm = recruteViewModel

            rvRecrute.apply {
                setHasFixedSize(true)
                adapter = recruteAdapter
                addItemDecoration(
                    CommonGridItemDecoration(
                        outerMargin = 20,
                        innerMargin = 6,
                        firstTopLength = 8,
                        top = 20)
                )
            }
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args?.let { bundle ->
            bundle.getString(SEARCH_WORK)?.let { searchWord ->
                recruteAdapter.searchCompanies(
                    filterWord = SearchFilterType.RECRUTE.value,
                    searchWord = searchWord)
            }
        }
    }

}