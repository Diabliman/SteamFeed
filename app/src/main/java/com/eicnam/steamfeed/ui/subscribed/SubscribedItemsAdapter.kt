package com.eicnam.steamfeed.ui.subscribed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.databinding.SearchRowLayoutBinding
import com.eicnam.steamfeed.databinding.SubscribedRowLayoutBinding
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.News
import com.eicnam.steamfeed.ui.feed.CustomAdapter
import com.eicnam.steamfeed.ui.search.SearchItemsAdapter
import com.eicnam.steamfeed.viewmodel.GameViewModel
import kotlinx.coroutines.*
import java.util.stream.Collectors

class SubscribedItemsAdapter(val viewModel : GameViewModel) :
    RecyclerView.Adapter<SubscribedItemsAdapter.ViewHolder>() {

    private var itemsList = emptyList<Game>()

    class ViewHolder(val binding: SubscribedRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SubscribedRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameItem = itemsList[position]
        holder.binding.textViewGameItem.text = gameItem.name
        holder.binding.buttonUnfollow.setOnClickListener {
            runBlocking {
                CoroutineScope(Dispatchers.IO).async {
                    viewModel.unSubscribe(gameItem.appid)
                    setData(viewModel.getSubbedGames() as ArrayList<Game>)
                    notifyItemRemoved(position)
                }.await()
            }
        }
    }



    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setData(gameItemList: ArrayList<Game>){
        this.itemsList = gameItemList
    }

    fun getData() : List<Game> {
        return this.itemsList
    }


}
