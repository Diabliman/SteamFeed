package com.eicnam.steamfeed.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.model.News
import com.squareup.picasso.Picasso


class CustomAdapter() :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    lateinit var mList: List<News>


    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_card_view_design, parent, false)
        return ViewHolder(view, mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val news = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView_title.text = news.title
        Picasso.get().load(news.getGameBanner()).into(holder.imageView_banner)
        holder.textView_feedname.text = news.feedlabel
        holder.textView_date.text = news.getDate()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(newsList: List<News>) {
        this.mList = newsList
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(ItemView) {
        val textView_title: TextView = itemView.findViewById(R.id.textView_title)
        var imageView_banner: ImageView = itemView.findViewById(R.id.imageView_banner)
        val textView_feedname: TextView = itemView.findViewById(R.id.textView_feedname)
        val textView_date: TextView = itemView.findViewById(R.id.textView_date)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}
