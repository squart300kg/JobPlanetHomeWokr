package com.jobplanet.kr.android.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @Expose
    val cell_items: List<CellItem>
) {
    data class CellItem(
        @Expose
        @SerializedName("cell_type")
        val cellType: String,
        @Expose
        @SerializedName("cons")
        val cons: String,
        @Expose
        @SerializedName("count")
        val count: Int,
        @Expose
        @SerializedName("industry_name")
        val industryName: String,
        @Expose
        @SerializedName("interview_question")
        val interviewQuestion: String,
        @Expose
        @SerializedName("logo_path")
        val logoPath: String,
        @Expose
        @SerializedName("name")
        val name: String,
        @Expose
        @SerializedName("pros")
        val pros: String,
        @Expose
        @SerializedName("rate_total_avg")
        val rate_total_avg: Double,
        @Expose
        @SerializedName("recommend_recruit")
        val recommend_recruit: List<RecommendRecruit>,
        @Expose
        @SerializedName("review_summary")
        val review_summary: String,
        @Expose
        @SerializedName("salary_avg")
        val salary_avg: Int,
        @Expose
        @SerializedName("section_title")
        val section_title: String,
        @Expose
        @SerializedName("update_date")
        val update_date: String
    ) {
        data class RecommendRecruit(
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
            val title: String
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
}