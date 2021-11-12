package com.eicnam.steamfeed.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.databinding.SearchRowLayoutBinding
import com.eicnam.steamfeed.model.Game

class SearchItemsAdapter : RecyclerView.Adapter<SearchItemsAdapter.ViewHolder>() {

    private var itemsList = emptyList<Game>()

    class ViewHolder(val binding: SearchRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchItem = itemsList[position]
        holder.binding.textViewSearchItem.text = searchItem.name
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setData(searchItemList: ArrayList<Game>){
        this.itemsList = searchItemList

    }


}
