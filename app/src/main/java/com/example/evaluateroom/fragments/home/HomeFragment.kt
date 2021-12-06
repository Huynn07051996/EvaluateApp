package com.example.evaluateroom.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.evaluateapp.viewmodel.MainActivityViewMoldel
import com.example.evaluateappfinal.adapter.EvaluateAdapter
import com.example.evaluateroom.R
import com.example.evaluateroom.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: MainActivityViewMoldel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: EvaluateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //DataBinding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = EvaluateAdapter {
            val action: NavDirections =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        //Setup Recyclerview
        binding.rcvHomeFragment.adapter = adapter
        viewModel.respone.observe(viewLifecycleOwner, {
            viewModel.checkDataEmpty(it)
            adapter.submitList(it)
        })

        return binding.root
    }

}