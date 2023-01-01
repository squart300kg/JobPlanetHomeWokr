package com.jobplanet.kr.android.ui.sub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jobplanet.kr.android.base.BaseViewModel
import com.jobplanet.kr.android.model.response.CommonRecruteItem
import com.jobplanet.kr.android.model.response.CompanyResponse
import com.jobplanet.kr.android.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val repository: CompanyRepository
) : BaseViewModel() {

    private val _companyResponse = MutableLiveData<MutableList<CompanyResponse.CellItem>>()
    val companyResponse: LiveData<MutableList<CompanyResponse.CellItem>>
        get() = _companyResponse

    fun getCompanies() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getCompanies()
                .catch {
                    // TODO: 어떻게 처리할지 고민
                }
                .collect { response ->
                    _companyResponse.value = response.cell_items.toMutableList().apply {
                        map { companyItem ->
                            companyItem.clickListener = clickListener
                            companyItem.recommendRecruit?.map { recruteItem ->
                                recruteItem.clickListener = clickListener
                            }
                        }
                    }
                }
        }
    }

    fun getCellItemByName(companyName: String): CompanyResponse.CellItem? {
        return companyResponse.value?.find { it.name == companyName }
    }

    fun getRecruteItem(rowIndex: Int, recruteId: Int): CommonRecruteItem? {
        return companyResponse.value?.get(rowIndex)?.recommendRecruit?.find { it.id == recruteId }
    }

}