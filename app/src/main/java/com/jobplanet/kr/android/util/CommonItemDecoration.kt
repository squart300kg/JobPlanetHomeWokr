package com.jobplanet.kr.android.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CommonItemDecoration(
    private val firstItemMargin: Int = -1,
    private val lastItemMargin: Int = -1,
    private val left: Int = 0,
    private val right: Int = 0,
    private val top: Int = 0,
    private val bottom: Int = 0,
    private val orientation: Int = LinearLayoutManager.HORIZONTAL
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left = DensityUtil.dpToPx(left)
        outRect.right = DensityUtil.dpToPx(right)
        outRect.top = DensityUtil.dpToPx(top)
        outRect.bottom = DensityUtil.dpToPx(bottom)

        val isFirst = parent.getChildAdapterPosition(view) == 0
        val isLast = parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount?: 0) - 1


        if (firstItemMargin >= 0 && isFirst) {
            when(orientation) {
                LinearLayoutManager.HORIZONTAL -> {
                    outRect.left = DensityUtil.dpToPx(firstItemMargin)
                }
                LinearLayoutManager.VERTICAL -> {
                    outRect.top = DensityUtil.dpToPx(firstItemMargin)
                }
            }
        }

        if (lastItemMargin >= 0 && isLast) {
            when(orientation) {
                LinearLayoutManager.HORIZONTAL -> {
                    outRect.right = DensityUtil.dpToPx(lastItemMargin)
                }
                LinearLayoutManager.VERTICAL -> {
                    outRect.bottom = DensityUtil.dpToPx(lastItemMargin)
                }
            }
        }
    }
}
