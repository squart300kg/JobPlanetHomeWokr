package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.databinding.ItemCompanyBinding
import com.jobplanet.kr.android.model.response.CompanyResponse

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    private val items: MutableList<CompanyResponse.CellItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyAdapter.CompanyViewHolder {
        return CompanyViewHolder(
            BR.companyItem,
            parent,
            R.layout.item_company)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {

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
//        init {
//            itemBinding.rvCompanyAppealCategory.apply {
//                setHasFixedSize(true)
//                adapter = RecruteAppealAdapter()
//                addItemDecoration(
//                    CommonItemDecoration(
//                        firstItemMargin = 0,
//                        lastItemMargin = 0,
//                        left = 2,
//                        right = 2
//                    )
//                )
//            }
//        }

        // TODO: 클릭이벤트 대비하여 남겨둠 추후 필요없으면 제거
        fun initClickTag() {  }
    }

}