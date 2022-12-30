package com.jobplanet.kr.android.model.response

import android.view.View.OnClickListener
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecrutesResponse(
    @Expose
    @SerializedName("recruit_items")
    val recruitItems: List<RecruitItem>
) {
    data class RecruitItem(
        @Expose
        @SerializedName("appeal")
        val appeal: String,
        @Expose
        @SerializedName("company")
        val company: Company,
        @Expose
        @SerializedName("id")
        val id: Int,
        @Expose
        @SerializedName("image_url")
        val imageUrl: String,
        @Expose
        @SerializedName("reward")
        val reward: Int,
        @Expose
        @SerializedName("title")
        val title: String,

        var clickListener: OnClickListener? = null
    ) {
        data class Company(
            @Expose
            @SerializedName("logo_path")
            val logoPath: String,
            @Expose
            @SerializedName("name")
            val name: String,
            @Expose
            @SerializedName("ratings")
            val ratings: List<Rating>
        ) {
            data class Rating(
                @Expose
                @SerializedName("rating")
                val rating: Double,
                @Expose
                @SerializedName("type")
                val type: String
            )
        }
    }
}