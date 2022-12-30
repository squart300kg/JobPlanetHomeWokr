package com.jobplanet.kr.android.ext

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.util.GlideUtil

@BindingAdapter("jobPlanet:loadImageRadius",
                "jobPlanet:radius",
                requireAll = true)
fun ImageView.loadImageRadius(imageUrl: String?, radius: Int?) {
    if (imageUrl != null && radius != null) {
        GlideUtil.loadImageRadius(
            imageView = this,
            url = imageUrl,
            radius = radius)
    }
}
