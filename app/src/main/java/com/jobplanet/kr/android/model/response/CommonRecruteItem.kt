package com.jobplanet.kr.android.model.response

import android.view.View
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommonRecruteItem(
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

    var clickListener: View.OnClickListener
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
