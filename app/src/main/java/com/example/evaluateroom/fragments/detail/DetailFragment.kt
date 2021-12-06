package com.example.evaluateroom.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.evaluateappfinal.viewmodel.DetailActivityViewModel
import com.example.evaluateroom.databinding.FragmentDetailBinding
import com.example.evaluateroom.model.Evaluate


class DetailFragment : Fragment() {

//    private val viewModel: DetailActivityViewModel by viewModels()
    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false)
        //Data Binding
        binding = FragmentDetailBinding.inflate(inflater, container, false)


        var evaluateInstance = arguments?.getParcelable("evaluate") as? Evaluate
        var evaluateId = evaluateInstance?.id
        viewModel = DetailActivityViewModel(evaluateInstance!!)
        if (evaluateId != null) {
            viewModel.evaluate.observe(viewLifecycleOwner, {
                Glide.with(binding.imageProductDetail).load(it.image)
                    .into(binding.imageProductDetail)
                binding.txvPriceDetail.text = it.price.toString()
                binding.txvTitleDetail.text = it.title
                binding.txvDescriptionDetail.text = it.description
            })
        }

        return binding.root
    }


}