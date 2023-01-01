package com.jobplanet.kr.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.jobplanet.kr.android.ext.checkSearchWord
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.ActivityCompanyDetailBinding
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

@AndroidEntryPoint
class CompanyDetailActivity: BaseActivity<ActivityCompanyDetailBinding>(R.layout.activity_company_detail) {

    /**
     * 상세화면 구현 로직입니다.
     * 해당 부분은 안내의 허락에 따라 임의로 개발하였음을 말씀드립니다.
     */
    companion object {
        val THUMBNAIL = "thumbnail"
        val COMPANY = "company"
        val TITLE = "title"
        val RATING = "rating"
        val REWARD = "reward"
        val APPEAL = "appeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            bundle = intent.extras
        }

    }

}