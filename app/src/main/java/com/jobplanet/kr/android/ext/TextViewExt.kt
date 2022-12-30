package com.jobplanet.kr.android.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.model.response.RecrutesResponse
import java.text.DecimalFormat

@BindingAdapter("jobPlanet:setText")
fun TextView.setText(text: String?) {
    text?.let { text ->
        this.text = text
    }
}

@BindingAdapter("jobPlanet:setReward")
fun TextView.setReward(reward: Int?) {
    reward?.let { reward ->
        text = DecimalFormat("#,###").format(reward) + resources.getString(R.string.won)
    }
}

@BindingAdapter("jobPlanet:calculateRatingAvg")
fun TextView.calculateRatingAvg(ratings: List<RecrutesResponse.RecruitItem.Company.Rating>?) {
    ratings?.let { ratings ->
        val scoresAvg = ratings.map { it.rating }.max()
        this.text = "$scoresAvg"
    }
}

