package com.eicnam.steamfeed.ui.subscribed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eicnam.steamfeed.databinding.FragmentSubscribedBinding

class SubscribedFragment : Fragment() {

    private lateinit var notificationsViewModel: SubscribedViewModel
    private var _binding: FragmentSubscribedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(SubscribedViewModel::class.java)

        _binding = FragmentSubscribedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSubscribed
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}