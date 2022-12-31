package com.jobplanet.kr.android.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecrutesResponse(
    @Expose
    @SerializedName("recruit_items")
    val recruitItems: List<CommonRecruteItem>
)