package com.jobplanet.kr.android.ui.sub

import android.os.Bundle
import android.view.View
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseFragment
import com.jobplanet.kr.android.databinding.FragmentCompanyBinding
import com.jobplanet.kr.android.util.CommonGridItemDecoration

class CompanyFragment: BaseFragment<FragmentCompanyBinding>(R.layout.fragment_company) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            rvCompany.apply {
                setHasFixedSize(true)
//                adapter = recruteAdapter
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
}