package com.jobplanet.kr.android.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import com.jobplanet.kr.android.ui.adapter.CategoryAdapter
import com.jobplanet.kr.android.ui.adapter.RecruteAdapter
import com.jobplanet.kr.android.ui.adapter.RecruteAppealAdapter

@BindingAdapter("jobPlanet:submit")
fun <T> RecyclerView.submit(items: List<T>?) {
    items?.let {
        when (adapter) {
            is CategoryAdapter -> {
                (adapter as CategoryAdapter).submit(items as List<SearchCategoryResponse.SearchCategory>)
            }
            is RecruteAdapter -> {
                (adapter as RecruteAdapter).submit(items as List<RecrutesResponse.RecruitItem>)
            }
            is RecruteAppealAdapter -> {
                (adapter as RecruteAppealAdapter).submit(items as List<String>)
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
