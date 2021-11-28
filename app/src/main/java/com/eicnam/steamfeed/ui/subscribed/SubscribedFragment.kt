package com.eicnam.steamfeed.ui.subscribed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eicnam.steamfeed.R
import com.eicnam.steamfeed.databinding.FragmentSubscribedBinding
import com.eicnam.steamfeed.model.Game
import com.eicnam.steamfeed.model.News
import com.eicnam.steamfeed.viewmodel.GameViewModel
import com.eicnam.steamfeed.viewmodel.GameViewModelFactory
import kotlinx.coroutines.*
import java.util.stream.Collectors

class SubscribedFragment :  Fragment() {

    private val subscribedViewModel: GameViewModel by viewModels { GameViewModelFactory(context) }
    private val subscribedAdapter: SubscribedItemsAdapter by lazy { SubscribedItemsAdapter(subscribedViewModel) }

    //private lateinit var searchViewModel: GameViewModel
    private var _binding: FragmentSubscribedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubscribedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Search View items init


        val recyclerview = root.findViewById<RecyclerView>(R.id.recyclerview_subscribed)
        _binding.apply {
            recyclerview.apply {
                recyclerview.adapter = subscribedAdapter
                recyclerview.layoutManager = LinearLayoutManager(root.context)
                setHasFixedSize(true)
            }
        }

        val list: List<Game> = runBlocking {
            CoroutineScope(Dispatchers.IO).async {
                subscribedViewModel.getSubbedGames()
            }.await()
        }
        subscribedAdapter.setData(list as ArrayList<Game>)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}