package com.jobplanet.kr.android.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.kr.android.BR
import com.jobplanet.kr.android.R
import com.jobplanet.kr.android.base.BaseViewHolder
import com.jobplanet.kr.android.constant.LayoutType
import com.jobplanet.kr.android.databinding.ItemRecruteBinding
import com.jobplanet.kr.android.model.response.CommonRecruteItem
import com.jobplanet.kr.android.util.CommonItemDecoration

class RecruteCommonAdapter(
    private val layoutType: LayoutType = LayoutType.GRID
) : RecyclerView.Adapter<RecruteCommonAdapter.RecruteViewHolder>() {

    private lateinit var ownerRecyclerView: RecyclerView

    private val items: MutableList<CommonRecruteItem> = mutableListOf()
    private var filterdItems: MutableList<CommonRecruteItem> = mutableListOf()

    private var filterWord = ""
    private var searchWord = ""

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        ownerRecyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecruteCommonAdapter.RecruteViewHolder {
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


    private fun getItemsBySearchWord(): MutableList<CommonRecruteItem> {
        return if (searchWord.isEmpty()) items
               else filterdItems
    }

    fun searchCompanies(filterWord: String, searchWord: String) {
        this.filterWord = filterWord
        this.searchWord = searchWord

        filterdItems.clear()
        filterdItems.addAll(
            items.filter { recruitItem ->
                recruitItem.title.contains(searchWord)
            }
        )
        notifyDataSetChanged()
    }

    fun submit(response: List<CommonRecruteItem>) {
        items.clear()
        items.addAll(response)
        notifyDataSetChanged()
    }

    inner class RecruteViewHolder(
        itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
    ): BaseViewHolder<CommonRecruteItem, ItemRecruteBinding>(itemId, parent, layoutRes) {
        init {
            if (layoutType == LayoutType.INNER_LINEAR_HORIZONTAL) {
                itemBinding.recruteRootView.apply { layoutParams.width = itemView.resources.getDimensionPixelSize(R.dimen.companyHorizontalCellTypeWidth) }
            }

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

        fun initClickTag() {
            val tag = if (layoutType == LayoutType.INNER_LINEAR_HORIZONTAL) {
                intArrayOf(ownerRecyclerView.tag as Int, getItemsBySearchWord()[absoluteAdapterPosition].id)
            } else {
                intArrayOf(getItemsBySearchWord()[absoluteAdapterPosition].id)
            }
            itemBinding.recruteRootView.tag = tag
        }
    }

}