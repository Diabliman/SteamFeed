package com.eicnam.steamfeed.ui.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.databinding.FragmentFeedBinding
import com.eicnam.steamfeed.ui.webactivity.WebviewActivity

class FeedFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel
    private var _binding: FragmentFeedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedViewModel =
            ViewModelProvider(this).get(FeedViewModel::class.java)

        //_binding = FragmentFeedBinding.inflate(inflater, container, false)
        //val root: View = binding.root

        feedViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        val root : ViewGroup = inflater.inflate(R.layout.fragment_feed, null) as ViewGroup;
        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerview_feed)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(root.context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel("News "+ i,
                "https://steamdb.info/",
                "Best game ever 10/10",
            "Game "+ i,
            "date here"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        adapter.setOnClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                activity?.let{
                //Toast.makeText(activity,data[position].url, Toast.LENGTH_SHORT).show()

                val intent = Intent(activity, WebviewActivity::class.java)
                    intent.putExtra("url", data[position].url)
                    it.startActivity(intent)
                }
            }

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}