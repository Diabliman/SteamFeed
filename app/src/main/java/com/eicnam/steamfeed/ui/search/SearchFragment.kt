package com.eicnam.steamfeed.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.databinding.FragmentSearchBinding
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.util.onQueryTextChanged
import com.eicnam.steamfeed.viewmodel.GameViewModel
import com.eicnam.steamfeed.viewmodel.GameViewModelFactory
import java.util.stream.Collectors

class SearchFragment : Fragment() {



    private val searchViewModel: GameViewModel by viewModels { GameViewModelFactory(context) }
    private val searchadapter: SearchItemsAdapter by lazy { SearchItemsAdapter(searchViewModel)}


    //private lateinit var searchViewModel: GameViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Search View items init

        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerview_search)
        _binding.apply {
            recyclerview.apply {
                recyclerview.adapter = searchadapter
                recyclerview.layoutManager = LinearLayoutManager(root.context)
                setHasFixedSize(true)

            }
        }
        searchViewModel.games.observe(this, {
            searchadapter.setData(it as ArrayList<Game>)
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val searchView = menu.findItem(R.id.searchView).actionView as SearchView

        menu.findItem(R.id.searchView).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.onQueryTextChanged { it ->
            val searchQuery = it
            searchViewModel.findGamesByNameStart(searchQuery).observe(this, Observer { list ->
                list.let {
                    searchadapter.setData(list as ArrayList<Game>)
                    searchadapter.notifyDataSetChanged()
                    Log.e("List = ", list.toString())
                }
            })
        }
    }
}