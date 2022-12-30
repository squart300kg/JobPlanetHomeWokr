package com.jobplanet.kr.android.model.response

import android.view.View.OnClickListener
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 카테고리는 서버 api로부터 확장의 가능성이 있기에 추가 설계했습니다.
 */
data class SearchCategoryResponse(

    @Expose
    @SerializedName("item")
    val item: List<SearchCategory>
) {
    data class SearchCategory(
        @Expose
        @SerializedName("title")
        val title: String,
        var clickListener: OnClickListener? = null
    )
}