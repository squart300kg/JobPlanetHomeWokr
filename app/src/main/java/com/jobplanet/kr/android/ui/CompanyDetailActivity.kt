package com.jobplanet.kr.android.ui

import android.os.Bundle
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.databinding.ActivityCompanyDetailBinding
import dagger.hilt.android.AndroidEntryPoint

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