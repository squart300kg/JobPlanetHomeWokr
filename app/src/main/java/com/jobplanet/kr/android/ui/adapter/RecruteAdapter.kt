package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.constant.SearchFilterType
import com.jobplanet.kr.android.databinding.ItemCompanyBinding
import com.jobplanet.kr.android.databinding.ItemRecruteBinding
import com.jobplanet.kr.android.model.response.RecrutesResponse
import com.jobplanet.kr.android.util.CommonItemDecoration

class RecruteAdapter : RecyclerView.Adapter<RecruteAdapter.RecruteViewHolder>() {

    private val items: MutableList<RecrutesResponse.RecruitItem> = mutableListOf()
    private var filterdItems: MutableList<RecrutesResponse.RecruitItem> = mutableListOf()

    private var filterWord = ""
    private var searchWord = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecruteAdapter.RecruteViewHolder {
        return RecruteViewHolder(
            BR.recruteItem,
            parent,
            R.layout.item_recrute)
    }

    override fun onBindViewHolder(holder: RecruteViewHolder, position: Int) {
        holder.bindItem(getItemsBySearchWord()[position])
        holder.initClickTag()
    }

    override fun getItemCount(): Int {
        return getItemsBySearchWord().count()
    }


    private fun getItemsBySearchWord(): MutableList<RecrutesResponse.RecruitItem> {
        return if (searchWord.isEmpty()) items
        else filterdItems
    }

    fun searchCompanies(filterWord: String, searchWord: String) {
        this.filterWord = filterWord
        this.searchWord = searchWord

        filterdItems.clear()
        filterdItems.addAll(
            items.filter { recruitItem ->
                when (filterWord) {
                    SearchFilterType.COMPANY.value -> {
                        recruitItem.company.name.contains(searchWord)
                    }
                    SearchFilterType.RECRUTE.value -> {
                        recruitItem.title.contains(searchWord)
                    }
                    else -> throw IllegalArgumentException("search filter type error")
                }
            }
        )
        notifyDataSetChanged()
    }

    fun submit(response: List<RecrutesResponse.RecruitItem>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

    inner class RecruteViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<RecrutesResponse.RecruitItem, ItemRecruteBinding>(itemId, parent, layoutRes) {
        init {
            itemBinding.rvCompanyAppealCategory.apply {
                setHasFixedSize(true)
                adapter = RecruteAppealAdapter()
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

        // TODO: 클릭이벤트 대비하여 남겨둠 추후 필요없으면 제거
        fun initClickTag() {  }
    }

}