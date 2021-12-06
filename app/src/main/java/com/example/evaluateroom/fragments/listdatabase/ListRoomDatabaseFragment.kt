package com.example.evaluateroom.fragments.listdatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.evaluateappfinal.viewmodel.EvaluateViewModel
import com.example.evaluateroom.adapter.EvaluateEntityAdapter
import com.example.evaluateroom.databinding.FragmentListRoomDatabaseBinding


class ListRoomDatabaseFragment : Fragment() {

    private val viewModel: EvaluateViewModel by viewModels()
    private lateinit var adapter: EvaluateEntityAdapter
    private lateinit var binding: FragmentListRoomDatabaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //DataBinding
        binding = FragmentListRoomDatabaseBinding.inflate(inflater, container, false)

        adapter = EvaluateEntityAdapter()

        binding.revListDatabase.adapter = adapter
        viewModel.readAllData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }


}