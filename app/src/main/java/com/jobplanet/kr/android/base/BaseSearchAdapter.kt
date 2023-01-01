package com.jobplanet.kr.android.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSearchAdapter<T, B: RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val items: MutableList<T> = mutableListOf()
    protected var filterdItems: MutableList<T> = mutableListOf()

    private var filterWord = ""
    private var searchWord = ""

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): B

    abstract override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemCount(): Int {
        return  getItemsBySearchWord().count()
    }

    protected fun getItemsBySearchWord(): MutableList<T> {
        return if (searchWord.isEmpty()) items
                else filterdItems
    }

    open fun searchCompanies(filterWord: String, searchWord: String, filter: (T) -> Boolean = { false }) {
        this.filterWord = filterWord
        this.searchWord = searchWord

        filterdItems.clear()
        filterdItems.addAll(
            items.filter { item ->
                filter(item)
            }
        )
        notifyDataSetChanged()
    }
}