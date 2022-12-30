package com.jobplanet.kr.android.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.util.DensityUtils


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

        outRect.left = DensityUtils.dpToPx(left)
        outRect.right = DensityUtils.dpToPx(right)
        outRect.top = DensityUtils.dpToPx(top)
        outRect.bottom = DensityUtils.dpToPx(bottom)

        val isFirst = parent.getChildAdapterPosition(view) == 0
        val isLast = parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount?: 0) - 1


        if (firstItemMargin >= 0 && isFirst) {
            when(orientation) {
                LinearLayoutManager.HORIZONTAL -> {
                    outRect.left = DensityUtils.dpToPx(firstItemMargin)
                }
                LinearLayoutManager.VERTICAL -> {
                    outRect.top = DensityUtils.dpToPx(firstItemMargin)
                }
            }
        }

        if (lastItemMargin >= 0 && isLast) {
            when(orientation) {
                LinearLayoutManager.HORIZONTAL -> {
                    outRect.right = DensityUtils.dpToPx(lastItemMargin)
                }
                LinearLayoutManager.VERTICAL -> {
                    outRect.bottom = DensityUtils.dpToPx(lastItemMargin)
                }
            }
        }
    }
}
