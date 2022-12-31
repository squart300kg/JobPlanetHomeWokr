package com.jobplanet.kr.android.ui.sub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jobplanet.kr.android.base.BaseViewModel
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.repository.RecruteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecruteViewModel @Inject constructor(
    private val repository: RecruteRepository
) : BaseViewModel() {

    private val _recruteResponse = MutableLiveData<MutableList<RecrutesResponse.RecruitItem>>()
    val recruteResponse: LiveData<MutableList<RecrutesResponse.RecruitItem>>
        get() = _recruteResponse

//    lateinit var clickListener: View.OnClickListener

    fun getRecrutes() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getCompanies()
                .catch {
                    // TODO: 어떻게 처리할지 고민
                }
                .collect { response ->
                    _recruteResponse.value = response.recruitItems.toMutableList().apply {
                        map { searchCategory ->
//                            searchCategory.clickListener = clickListener
                        }
                    }
                }
        }

    }
}