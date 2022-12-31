package com.jobplanet.kr.android.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.model.response.CommonRecruteItem
import com.jobplanet.kr.android.model.response.CompanyResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import com.jobplanet.kr.android.ui.adapter.CategoryAdapter
import com.jobplanet.kr.android.ui.adapter.CompanyAdapter
import com.jobplanet.kr.android.ui.adapter.RecruteCommonAdapter
import com.jobplanet.kr.android.ui.adapter.RecruteAppealAdapter

@BindingAdapter("jobPlanet:submit")
fun <T> RecyclerView.submit(items: List<T>?) {
    items?.let {
        when (adapter) {
            is CategoryAdapter -> {
                (adapter as CategoryAdapter).submit(items as List<SearchCategoryResponse.SearchCategory>)
            }
            is RecruteCommonAdapter -> {
                (adapter as RecruteCommonAdapter).submit(items as List<CommonRecruteItem>)
            }
            is RecruteAppealAdapter -> {
                (adapter as RecruteAppealAdapter).submit(items as List<String>)
            }
            is CompanyAdapter -> {
                (adapter as CompanyAdapter).submit(items as List<CompanyResponse.CellItem>)
            }
        }
    }
}

@BindingAdapter("jobPlanet:submit")
fun RecyclerView.submit(items: String?) {
    items?.let {
        submit(it.split(",").toList())
    }
}
