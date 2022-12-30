package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.databinding.ItemCategoryBinding
import com.jobplanet.kr.android.model.response.SearchCategoryResponse

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.SearchCategoryViewHolder>() {

    private val items: MutableList<SearchCategoryResponse.SearchCategory> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.SearchCategoryViewHolder {
            return SearchCategoryViewHolder(
                BR.categoryItem,
                parent,
                R.layout.item_category
            )
    }

    override fun onBindViewHolder(holder: SearchCategoryViewHolder, position: Int) {
        holder.bindItem(items[position])
        holder.initTag()
    }

    override fun getItemCount(): Int {
        return items.count()
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
        fun initTag() {
            itemBinding.tvSearchCategory.tag = items[absoluteAdapterPosition].title
        }
    }
}