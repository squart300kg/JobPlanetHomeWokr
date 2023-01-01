package com.jobplanet.kr.android.util

import android.content.res.Resources
import android.util.TypedValue

object DensityUtil {
    fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }
}