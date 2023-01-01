package com.jobplanet.kr.android.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions

object GlideUtil {
    fun loadImageRadius(imageView: ImageView, url: String?, drawable: Drawable? = null, radius: Int,
                        topLeftAvailable : Boolean = true,
                        topRightAvailable : Boolean = true,
                        bottomRightAvailable : Boolean = true,
                        bottomLeftAvailable : Boolean = true) {

        val radiusPx = DensityUtil.dpToPx(radius).toFloat()

        Glide.with(imageView.context)
            .load(url)
            .transform(
                CenterCrop(),
                GranularRoundedCorners(
                    if (topLeftAvailable) radiusPx else 0.0f,
                    if (topRightAvailable) radiusPx else 0.0f,
                    if (bottomRightAvailable) radiusPx else 0.0f,
                    if (bottomLeftAvailable) radiusPx else 0.0f)
            )
            .placeholder(drawable)
            .error(drawable)
            .into(imageView)
    }
}