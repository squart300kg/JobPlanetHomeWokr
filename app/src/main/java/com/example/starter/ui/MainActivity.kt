package com.example.starter.ui

import android.os.Bundle
import com.example.starter.R
import com.example.starter.base.BaseActivity
import com.example.starter.databinding.ActivityMainBinding
import com.example.starter.util.BackButtonCloseHandler

// TODO:
//  1. buildGradle의 import 라이브러리 신기술 찾아보고 적용
//  2. KOIN -> HILT전환
//  3. 테스트 코드 작성

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val backButtonCloseHandler = BackButtonCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.run { }
    }

    override fun onBackPressed() {
        backButtonCloseHandler.appExit()
    }
}