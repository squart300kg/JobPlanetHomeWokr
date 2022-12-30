package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.databinding.ItemCompanyBinding
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.ui.CommonItemDecoration

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    private val items: MutableList<RecrutesResponse.RecruitItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyAdapter.CompanyViewHolder {
        return CompanyViewHolder(
            BR.companyItem,
            parent,
            R.layout.item_company
        )}

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItem(items[position])
        holder.initTag()
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun submit(response: List<RecrutesResponse.RecruitItem>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

    inner class CompanyViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<RecrutesResponse.RecruitItem, ItemCompanyBinding>(itemId, parent, layoutRes) {
        init {
            itemBinding.rvCompanyAppealCategory.apply {
                setHasFixedSize(true)
                adapter = CompanyAppealAdapter()
                addItemDecoration(
                    CommonItemDecoration(
                        firstItemMargin = 0,
                        lastItemMargin = 0,
                        left = 2,
                        right = 2
                    )
                )
            }
        }
        fun initTag() {  }
    }

}