package com.jobplanet.kr.android.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CommonGridItemDecoration(
    private val outerMargin: Int = 0,
    private val innerMargin: Int = 0,
    private val firstTopLength: Int = -1,
    private val lastBottomLength: Int = -1,
    private val row: Int = 2,
    private val top: Int = 0,
    private val bottom: Int = 0,
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = DensityUtil.dpToPx(top)
        outRect.bottom = DensityUtil.dpToPx(bottom)

        val childItemPosition = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount?: 0

        if (firstTopLength >= 0 && childItemPosition < row) {
            outRect.top = DensityUtil.dpToPx(firstTopLength)
        }

        if (lastBottomLength >= 0 && childItemPosition > itemCount - row) {
            outRect.bottom = DensityUtil.dpToPx(lastBottomLength)
        }

        when(childItemPosition % row) {
            0 -> {
                outRect.left = DensityUtil.dpToPx(outerMargin)
                outRect.right = DensityUtil.dpToPx(innerMargin)
            }
            row -1 -> {
                outRect.left = DensityUtil.dpToPx(innerMargin)
                outRect.right = DensityUtil.dpToPx(outerMargin)
            }
            else -> {
                outRect.left = DensityUtil.dpToPx(innerMargin)
                outRect.right = DensityUtil.dpToPx(innerMargin)
            }
        }
    }
}
