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
        val cons: String?,
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
        val name: String?,
        @Expose
        @SerializedName("pros")
        val pros: String?,
        @Expose
        @SerializedName("rate_total_avg")
        val rateTotalAvg: Double,
        @Expose
        @SerializedName("recommend_recruit")
        val recommendRecruit: List<CommonRecruteItem>,
        @Expose
        @SerializedName("review_summary")
        val reviewSummary: String,
        @Expose
        @SerializedName("salary_avg")
        val salaryAvg: Int?,
        @Expose
        @SerializedName("section_title")
        val sectionTitle: String,
        @Expose
        @SerializedName("update_date")
        val updateDate: String
    )
}