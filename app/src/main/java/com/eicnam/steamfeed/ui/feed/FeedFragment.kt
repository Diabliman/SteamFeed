package com.eicnam.steamfeed.ui.feed

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.databinding.FragmentFeedBinding
import com.eicnam.steamfeed.model.News
import com.eicnam.steamfeed.ui.webactivity.WebviewActivity
import com.eicnam.steamfeed.viewmodel.GameViewModel
import com.eicnam.steamfeed.viewmodel.GameViewModelFactory

class FeedFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel
    private var _binding: FragmentFeedBinding? = null
    private val searchViewModel: GameViewModel by viewModels { GameViewModelFactory(requireContext()) }
    private val customAdapter: CustomAdapter by lazy { CustomAdapter() }
    private lateinit var newsList: MutableList<News>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerview_feed)
        // this creates a vertical layout Manager
        _binding.apply {
            recyclerview.apply {
                recyclerview.adapter = customAdapter
                recyclerview.layoutManager = LinearLayoutManager(root.context)
                setHasFixedSize(true)
            }
        }
        // ArrayList of class News

        newsList = searchViewModel.getNews() as MutableList<News>

        newsList.sortByDescending { it.date }

        customAdapter.setData(newsList)

        // Setting the Adapter with the recyclerview
        customAdapter.setOnClickListener(object : CustomAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                activity?.let {
                    //Toast.makeText(activity,data[position].url, Toast.LENGTH_SHORT).show()

                    val intent = Intent(activity, WebviewActivity::class.java)
                    Log.e("URL=", newsList[position].url.toString())
                    intent.putExtra("url", newsList[position].url)
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