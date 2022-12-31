package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.databinding.ItemCompanyAppealCategoryBinding

class RecruteAppealAdapter : RecyclerView.Adapter<RecruteAppealAdapter.CompanyAppealViewHolder>() {

    private val items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecruteAppealAdapter.CompanyAppealViewHolder {
        return CompanyAppealViewHolder(
            BR.appealItem,
            parent,
            R.layout.item_company_appeal_category)
    }

    override fun onBindViewHolder(holder: CompanyAppealViewHolder, position: Int) {
        holder.bindItem(items[position])
        holder.initClickTag()
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun submit(response: List<String>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

    inner class CompanyAppealViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<String, ItemCompanyAppealCategoryBinding>(itemId, parent, layoutRes) {

        fun initClickTag() {

        }
    }

}