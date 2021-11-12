package com.eicnam.steamfeed.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.databinding.SearchRowLayoutBinding
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.viewmodel.GameViewModel
import kotlinx.coroutines.*
import java.util.stream.Collectors

class SearchItemsAdapter(val viewModel : GameViewModel) : RecyclerView.Adapter<SearchItemsAdapter.ViewHolder>() {

    private var itemsList = emptyList<Game>()

    class ViewHolder(val binding: SearchRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            SearchRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchItem = itemsList[position]
        holder.binding.textViewSearchItem.text = searchItem.name

        val list : List<String> = runBlocking {
            CoroutineScope(Dispatchers.IO).async {
                viewModel.getSubbedGames().stream().map { it.appid }.collect(Collectors.toList())
            }.await()
        }

        if(list.contains(searchItem.appid)){
            holder.binding.buttonFollow.text = "Subbed"
        }

        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                holder.binding.buttonFollow.setOnClickListener {
                    viewModel.subscribe(searchItem.appid)
                    notifyItemChanged(position)
                }
            }
        }


    }



    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setData(searchItemList: ArrayList<Game>){
        this.itemsList = searchItemList

    }

    fun getData() : List<Game> {
        return this.itemsList
    }


}
