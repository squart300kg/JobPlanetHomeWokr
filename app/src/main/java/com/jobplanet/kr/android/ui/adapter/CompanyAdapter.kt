package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.constant.CompanyType
import com.jobplanet.kr.android.databinding.ItemCompanyBinding
import com.jobplanet.kr.android.databinding.ItemRecruteCellTypeBinding
import com.jobplanet.kr.android.model.response.CompanyResponse
import com.jobplanet.kr.android.util.CommonItemDecoration

class CompanyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<CompanyResponse.CellItem> = mutableListOf()

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
            is CompanyViewHolder -> holder.bindItem(items[position])
            is TodayRecommentRecruteViewHolder -> holder.bindItem(items[position])
            is ReviewViewHolder -> holder.bindItem(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.count()
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
        // TODO: 클릭이벤트 대비하여 남겨둠 추후 필요없으면 제거
        fun initClickTag() {  }
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
                    adapter = RecruteCommonAdapter(isFromHome = false)
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

        // TODO: 클릭이벤트 대비하여 남겨둠 추후 필요없으면 제거
        fun initClickTag() {  }
    }

    /**
     * APi로 넘겨받는 cellType중, CELL_TYPE_REVIEW에 관한 viewHolder입니다.
     * 해당 부분은 디자인 시안에 보이지 않아 개발하지 않고 ViewHolder만 남겨놓았습니다.
      */
    inner class ReviewViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<CompanyResponse.CellItem, ItemCompanyBinding>(itemId, parent, layoutRes) {

        // TODO: 클릭이벤트 대비하여 남겨둠 추후 필요없으면 제거
        fun initClickTag() {  }
    }

}