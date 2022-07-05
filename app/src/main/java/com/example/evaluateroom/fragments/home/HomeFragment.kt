package com.example.evaluateroom.fragments.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.evaluateapp.viewmodel.MainActivityViewMoldel
import com.example.evaluateappfinal.adapter.EvaluateAdapter
import com.example.evaluateroom.api.Status
import com.example.evaluateroom.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


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
//        viewModel.respone.observe(viewLifecycleOwner) {
//            viewModel.checkDataEmpty(it)
//            adapter.submitList(it)
//        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.evaluateState.collect {
                    when (it.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            it.data?.let { list ->
                                viewModel.checkDataEmpty(list)
                                adapter.submitList(list)
                                Log.e(TAG, "onStart: ${lifecycle.currentState}")
                                Log.e(TAG, "onStart2: ${viewModel.evaluateState.value}")
                            }
                        }
                        else -> {
                            Toast.makeText(requireActivity(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                            Log.e(TAG, "${it.message}")
                        }
                    }
                }
            }
        }
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ${lifecycle.currentState}")
        Log.e(TAG, "onStop2: ${viewModel.evaluateState}")
    }

}