package com.jobplanet.kr.android.ui

import android.os.Bundle
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseActivity
import com.jobplanet.kr.android.databinding.ActivityRecruitsBinding
import com.jobplanet.kr.android.util.BackButtonCloseHandler
import dagger.hilt.android.AndroidEntryPoint

// TODO:
//  1. buildGradle의 import 라이브러리 신기술 찾아보고 적용
//  2. KOIN -> HILT전환
//  3. 테스트 코드 작성

@AndroidEntryPoint
class RecruitesActivity: BaseActivity<ActivityRecruitsBinding>(R.layout.activity_recruits) {

    private val backButtonCloseHandler = BackButtonCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {  }.run { }
    }

    override fun onBackPressed() {
        backButtonCloseHandler.appExit()
    }
}