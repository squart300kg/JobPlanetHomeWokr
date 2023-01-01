package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseSearchAdapter
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.constant.CompanyType
import com.jobplanet.kr.android.constant.LayoutType
import com.jobplanet.kr.android.databinding.ItemCompanyBinding
import com.jobplanet.kr.android.databinding.ItemRecruteCellTypeBinding
import com.jobplanet.kr.android.model.response.CommonRecruteItem
import com.jobplanet.kr.android.model.response.CompanyResponse
import com.jobplanet.kr.android.util.CommonItemDecoration

class CompanyAdapter: BaseSearchAdapter<CompanyResponse.CellItem, RecyclerView.ViewHolder>() {

    private val recruteCommonAdapter: RecruteCommonAdapter by lazy { RecruteCommonAdapter(layoutType = LayoutType.INNER_LINEAR_HORIZONTAL) }

    override fun getItemViewType(position: Int): Int {
        return CompanyType.valueOf(items[position].cellType).ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return when (viewType) {
            CompanyType.CELL_TYPE_COMPANY.ordinal ->
                CompanyViewHolder(
                    BR.companyItem,
                    parent,
                    R.layout.item_company)
            CompanyType.CELL_TYPE_HORIZONTAL_THEME.ordinal ->
                TodayRecommentRecruteViewHolder(
                    BR.recruteItem,
                    parent,
                    R.layout.item_recrute_cell_type)
            CompanyType.CELL_TYPE_REVIEW.ordinal ->
                ReviewViewHolder(
                    BR.companyItem,
                    parent,
                    R.layout.item_company)
            else -> throw IllegalArgumentException("company view type error")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CompanyViewHolder -> {
                holder.bindItem(getItemsBySearchWord()[position])
                holder.initClickTag()
            }
            is TodayRecommentRecruteViewHolder -> {
                holder.bindItem(getItemsBySearchWord()[position])
                holder.initClickTag()
            }
            is ReviewViewHolder -> {
                holder.bindItem(getItemsBySearchWord()[position])
                holder.initClickTag()
            }
        }
    }

    override fun searchCompanies(filterWord: String, searchWord: String, filter: (CompanyResponse.CellItem) -> Boolean) {
        super.searchCompanies(filterWord, searchWord) { recruitItem ->
            recruitItem.name?.let { companyName ->
                companyName.contains(searchWord)
            } ?: run { false }
        }
    }

    fun submit(response: List<CompanyResponse.CellItem>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

    inner class CompanyViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<CompanyResponse.CellItem, ItemCompanyBinding>(itemId, parent, layoutRes) {
        fun initClickTag() {
            itemBinding.companyRootView.tag = getItemsBySearchWord()[absoluteAdapterPosition].name
        }
    }

    inner class TodayRecommentRecruteViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<CompanyResponse.CellItem, ItemRecruteCellTypeBinding>(itemId, parent, layoutRes) {
        init {
            with (itemBinding) {
                rvRecrute.apply {
                    setHasFixedSize(true)
                    adapter = recruteCommonAdapter
                    addItemDecoration(
                        CommonItemDecoration(
                            firstItemMargin = 20,
                            left = 6,
                            right = 6
                        )
                    )
                }
            }
        }

        fun initClickTag() {
            itemBinding.rvRecrute.tag = absoluteAdapterPosition
        }

    }

    /**
     * CELL_TYPE_REVIEW에 관한 viewHolder입니다.
     * 해당 부분은 디자인 시안에 보이지 않아 임의로 데이터를 바인딩하여 구현하였습니다.
      */
    inner class ReviewViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<CompanyResponse.CellItem, ItemCompanyBinding>(itemId, parent, layoutRes) {
        fun initClickTag() {
            itemBinding.companyRootView.tag = absoluteAdapterPosition
        }
    }

}