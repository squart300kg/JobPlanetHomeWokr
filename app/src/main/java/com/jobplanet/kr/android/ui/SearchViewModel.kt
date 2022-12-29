package com.jobplanet.kr.android.ui

import android.util.Log
import com.jobplanet.kr.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel() {

    fun test() {
        Log.i("JOBPLE","SearchViewModel Hello")
    }
}