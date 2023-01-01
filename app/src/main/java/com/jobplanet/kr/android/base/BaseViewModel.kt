package com.jobplanet.kr.android.base

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel: ViewModel() {

    protected var job: Job? = null

    lateinit var clickListener: View.OnClickListener

    override fun onCleared() {
        super.onCleared()
        /**
         *  메모리 누수 방지코드입니다.
         */
        viewModelScope.cancel()
    }

}