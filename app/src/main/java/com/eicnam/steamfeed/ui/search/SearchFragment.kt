package com.eicnam.steamfeed.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.databinding.FragmentSearchBinding
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.util.onQueryTextChanged
import com.eicnam.steamfeed.viewmodel.GameViewModel
import com.eicnam.steamfeed.viewmodel.GameViewModelFactory

class SearchFragment : Fragment() {

    private val searchViewModel : GameViewModel by viewModels { GameViewModelFactory(context) }
    //private lateinit var searchViewModel: GameViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        //searchViewModel = ViewModelProvider(this)[GameViewModel::class.java]


        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root



  /*      searchViewModel.text.observe(viewLifecycleOwner, Observer {

        })
*/
        //Search View items init
        val itemModelList = ArrayList<Game>()



        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerview_search)

        val adapter = SearchItemsAdapter()
        _binding.apply {
            recyclerview.apply {
                recyclerview.adapter = adapter
                adapter.setData(itemModelList)
                recyclerview.layoutManager = LinearLayoutManager(root.context)
                setHasFixedSize(true)

            }
        }
        searchViewModel.games.observe(viewLifecycleOwner){
            adapter.setData(it as ArrayList<Game>)
        }
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
/*        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchView.onQueryTextChanged {

                }
                return true
            }
        })    */
        searchView.onQueryTextChanged {
            searchViewModel.searchQuery.value = it
        }
    }

}