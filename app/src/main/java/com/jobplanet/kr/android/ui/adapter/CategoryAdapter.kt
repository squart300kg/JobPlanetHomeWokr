package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.databinding.ItemCategoryBinding
import com.jobplanet.kr.android.ext.selectFilter
import com.jobplanet.kr.android.model.response.SearchCategoryResponse

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.SearchCategoryViewHolder>() {

    private val items: MutableList<SearchCategoryResponse.SearchCategory> = mutableListOf()

    private var currentSelectedPosition = 0
    private var lastSelectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.SearchCategoryViewHolder {
            return SearchCategoryViewHolder(
                BR.categoryItem,
                parent,
                R.layout.item_category
            )
    }

    // 첫 초기화시에만 사용하도록 설계했습니다.
    override fun onBindViewHolder(holder: SearchCategoryViewHolder, position: Int) {
        holder.bindItem(items[position])
        holder.initClickTag()
        if (position == 0) holder.selectFilter(isSelected = true)
    }

    // 갱신할때는 payload값을 핸들링하여 사용합니다.
    override fun onBindViewHolder(
        holder: SearchCategoryViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.forEach { payload ->
                holder.selectFilter(payload as Boolean)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun selectFilter(position: Int) {
        currentSelectedPosition = position
        notifyItemChanged(currentSelectedPosition, true)
        notifyItemChanged(lastSelectedPosition, false)
        lastSelectedPosition = currentSelectedPosition
    }

    fun submit(response: List<SearchCategoryResponse.SearchCategory>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

    inner class SearchCategoryViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<SearchCategoryResponse.SearchCategory, ItemCategoryBinding>(itemId, parent, layoutRes) {
        fun initClickTag() {
            itemBinding.tvSearchCategory.tag = Pair(
                absoluteAdapterPosition,
                items[absoluteAdapterPosition].title)
        }

        fun selectFilter(isSelected: Boolean) {
            itemBinding.tvSearchCategory.selectFilter(isSelected)
        }
    }
}