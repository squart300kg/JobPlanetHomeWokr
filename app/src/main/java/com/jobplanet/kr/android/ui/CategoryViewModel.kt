package com.jobplanet.kr.android.ui

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
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { response ->
                    _categoryResponse.value = response.item.toMutableList().apply {
                        map { searchCategory ->
                            searchCategory.clickListener = clickListener
                        }
                    }
                }
        }
    }
}