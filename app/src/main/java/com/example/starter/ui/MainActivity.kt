package com.example.starter.ui

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.example.starter.R
import com.example.starter.base.BaseActivity
import com.example.starter.databinding.ActivityMainBinding
import com.example.starter.ext.init
import com.example.starter.util.BackButtonCloseHandler
import org.koin.android.ext.android.inject

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val backButtonCloseHandler = BackButtonCloseHandler(this)

    private val idToFragmentMap: SparseArray<Fragment> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.run {
            bottomNavigation.init(
                fragmentManager = supportFragmentManager,
                containerId = mainNavFragment.id,
                idToFragmentMap = idToFragmentMap)
        }
    }

    override fun onBackPressed() {
        when (dataBinding.bottomNavigation.selectedItemId) {
            R.id.firstTabFragment -> backButtonCloseHandler.appExit()
            else -> super.onBackPressed()
        }
    }
}