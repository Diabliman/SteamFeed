package com.eicnam.steamfeed.ui.feed

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.google.android.material.internal.ContextUtils


class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener){
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

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView_title.text = ItemsViewModel.title
        holder.textView_contents.text = ItemsViewModel.contents
        holder.textView_name.text = ItemsViewModel.name
        holder.textView_date.text = ItemsViewModel.date
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener : onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val textView_title: TextView = itemView.findViewById(R.id.textView_title)
        val textView_contents: TextView = itemView.findViewById(R.id.textView_content)
        val textView_name: TextView = itemView.findViewById(R.id.textView_name)
        val textView_date: TextView = itemView.findViewById(R.id.textView_date)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
}
