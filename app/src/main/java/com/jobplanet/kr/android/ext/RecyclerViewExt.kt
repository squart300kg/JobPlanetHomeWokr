package com.jobplanet.kr.android.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import com.jobplanet.kr.android.ui.adapter.CategoryAdapter
import com.jobplanet.kr.android.ui.adapter.CompanyAdapter

@BindingAdapter("jobPlanet:submit")
fun <T> RecyclerView.submit(items: List<T>?) {
    items?.let {
        when (adapter) {
            is CategoryAdapter -> {
                (adapter as CategoryAdapter).submit(items as List<SearchCategoryResponse.SearchCategory>)
            }
            is CompanyAdapter -> {
                (adapter as CompanyAdapter).submit(items as List<RecrutesResponse.RecruitItem>)
            }
        }
    }
}
