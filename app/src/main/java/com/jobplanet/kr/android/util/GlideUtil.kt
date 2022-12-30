package com.jobplanet.kr.android.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions

object GlideUtil {
    fun loadImageCenterCrop(imageView: ImageView, url: String?, drawable: Drawable?) {
        Glide.with(imageView.context)
            .load(url)
            .transform(
                CenterCrop()
            )
            .placeholder(drawable)
            .error(drawable)
            .into(imageView)
    }

    fun loadImageFitCenter(imageView: ImageView, url: String?, drawable: Drawable?) {
        Glide.with(imageView.context)
            .load(url)
            .transform(
                FitCenter()
            )
            .placeholder(drawable)
            .error(drawable)
            .into(imageView)
    }

    fun loadImage(imageView: ImageView, url: String?, drawable: Drawable?) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(drawable)
            .error(drawable)
            .into(imageView)
    }

    fun loadImageRadius(imageView: ImageView, url: String?, drawable: Drawable? = null, radius: Int,
                        topLeftAvailable : Boolean = true,
                        topRightAvailable : Boolean = true,
                        bottomRightAvailable : Boolean = true,
                        bottomLeftAvailable : Boolean = true) {

        val radiusPx = DensityUtils.dpToPx(radius).toFloat()

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

    fun loadImageCircle(imageView: ImageView, url: String?, drawable: Drawable?) {
        Glide.with(imageView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .circleCrop()
            )
            .placeholder(drawable)
            .error(drawable)
            .into(imageView)
    }
}