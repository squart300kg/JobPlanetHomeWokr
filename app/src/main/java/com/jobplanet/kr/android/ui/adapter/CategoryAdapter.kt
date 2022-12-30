package com.jobplanet.kr.android.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.databinding.ItemCategoryBinding
import com.jobplanet.kr.android.model.response.SearchCategoryResponse
import com.jobplanet.kr.android.ui.RecruitesActivity

class CategoryAdapter(
    private val context: Context
) : RecyclerView.Adapter<CategoryAdapter.SearchCategoryViewHolder>() {

    private val items: MutableList<SearchCategoryResponse.SearchCategory> = mutableListOf()

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
        holder.initTag()
    }

    // 갱신할때는 payload값으로 식별 후, 사용합니다.
    override fun onBindViewHolder(
        holder: SearchCategoryViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            // 추후 payload의 추가 가능성을 생각하여 학장성에 대비하였습니다.
            payloads.forEach { payload ->
                when (payload as String) {
                    context.resources.getString(R.string.payLoadMessageCategoryClick) -> holder.selectFilter()
                }
            }
        }
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
            itemBinding.tvSearchCategory.tag = absoluteAdapterPosition
        }
        fun selectFilter() {
            with (itemBinding.tvSearchCategory) {
                isSelected = !isSelected
            }
        }
    }
}