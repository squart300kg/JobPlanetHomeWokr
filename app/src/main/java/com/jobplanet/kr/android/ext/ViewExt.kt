package com.jobplanet.kr.android.ext

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("jobPlanet:setVisibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}