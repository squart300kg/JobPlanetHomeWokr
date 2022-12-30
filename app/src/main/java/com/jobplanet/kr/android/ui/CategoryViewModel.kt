package com.jobplanet.kr.android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jobplanet.kr.android.base.BaseViewModel
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import com.jobplanet.kr.android.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : BaseViewModel() {

    private val _categoryResponse = MutableLiveData<MutableList<SearchCategoryResponse.SearchCategory>>()
    val categoryResponse: LiveData<MutableList<SearchCategoryResponse.SearchCategory>>
        get() = _categoryResponse

    fun getSearchCategorys() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getCategories()
                .flowOn(Dispatchers.IO)
                .catch {
                    // TODO: 어떻게 처리할지 고민
                }
                .collect {
                    _categoryResponse.value = it.item.toMutableList()
                }
        }
    }
}