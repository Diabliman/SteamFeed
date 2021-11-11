package com.eicnam.steamfeed.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.ui.feed.CustomAdapter

/*
class SearchItemsAdapter : RecyclerView.Adapter<SearchItemsAdapter.ViewHolder>() {

    private lateinit var itemsList : ArrayList<Game>

    fun setData(searchItemList: ArrayList<Game>){
        this.itemsList = searchItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_row_layout, parent, false)
        return SearchItemsAdapter.ViewHolder(view, itemsList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchItem = itemsList[position]
        holder.textView_search_item.text = searchItem.name
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ViewHolder(ItemView: View, listener: ArrayList<Game>) : RecyclerView.ViewHolder(ItemView) {
        val textView_search_item: TextView = itemView.findViewById(R.id.textView_search_item)
    }


}*/

class SearchItemsAdapter : RecyclerView.Adapter<SearchItemsAdapter.ViewHolder>() {

    private lateinit var itemsList : ArrayList<Game>

    fun setData(searchItemList: ArrayList<Game>){
        this.itemsList = searchItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_row_layout, parent, false)
        return SearchItemsAdapter.ViewHolder(view, itemsList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchItem = itemsList[position]
        holder.textView_search_item.text = searchItem.name
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ViewHolder(ItemView: View, listener: ArrayList<Game>) : RecyclerView.ViewHolder(ItemView) {
        val textView_search_item: TextView = itemView.findViewById(R.id.textView_search_item)
    }


}
