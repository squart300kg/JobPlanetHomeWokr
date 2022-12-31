package com.jobplanet.kr.android.ext

import android.graphics.Typeface
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.model.response.CommonRecruteItem
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.util.DateUtil
import java.text.DecimalFormat

@BindingAdapter("jobPlanet:setText")
fun TextView.setText(text: String?) {
    text?.let { text ->
        this.text = text
    }
}

@BindingAdapter("jobPlanet:setTime")
fun TextView.setTime(time: String?) {
    time?.let { time ->
        this.text = DateUtil.getDateString(time)
    }
}

@BindingAdapter("jobPlanet:setSalaryAvg")
fun TextView.setSalaryAvg(salary: Int?) {
    salary?.let {
        setCdataText(String.format(
            resources.getString(R.string.salaryAvg),
            String.format(DecimalFormat("#,###").format(it)))
        )
    }
}

@BindingAdapter("jobPlanet:setReward")
fun TextView.setReward(reward: Int?) {
    reward?.let { reward ->
        text = String.format(
            resources.getString(R.string.reward),
            DecimalFormat("#,###").format(reward)
        )
    }
}

@BindingAdapter("jobPlanet:calculateRatingAvg")
fun TextView.calculateRatingAvg(ratings: List<CommonRecruteItem.Company.Rating>?) {
    ratings?.let { ratings ->
        val scoresAvg = ratings.map { it.rating }.max()
        this.text = "$scoresAvg"
    }
}

fun TextView.selectFilter(isSelected: Boolean) {
    this.isSelected = isSelected
    this.typeface = if (this.isSelected) {
        Typeface.DEFAULT_BOLD
    } else {
        Typeface.DEFAULT
    }
}

fun TextView.setCdataText(string: String) {
    text = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(string)
    } else {
        Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    }
}
