package com.jobplanet.kr.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.securepreferences.SecurePreferences
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel: ViewModel() {

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        // 메모리 누수 방지
        viewModelScope.cancel()
    }

}