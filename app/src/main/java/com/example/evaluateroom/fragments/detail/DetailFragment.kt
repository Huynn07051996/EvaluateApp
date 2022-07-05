package com.example.evaluateroom.fragments.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.evaluateroom.viewmodel.DetailActivityViewModel
import com.example.evaluateroom.databinding.FragmentDetailBinding
import com.example.evaluateroom.model.Evaluate
import kotlinx.coroutines.delay


class DetailFragment : Fragment() {

//    private val viewModel: DetailActivityViewModel by viewModels()
    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var binding: FragmentDetailBinding

    @SuppressLint("SetTextI12sp")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data Binding
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        var evaluateInstance = arguments?.getParcelable("evaluate") as? Evaluate
        var evaluateId = evaluateInstance?.id
        viewModel = DetailActivityViewModel(evaluateInstance!!)
        binding.detailActivityViewModel = viewModel
        if (evaluateId != null) {
            viewModel.evaluate.observe(viewLifecycleOwner) {
                Glide.with(binding.imageProductDetail).load(it.image)
                    .into(binding.imageProductDetail)
                binding.txvPriceDetail.text = it.price.toString()
                binding.txvTitleDetail.text = "Title: ${it.title}"
                binding.txvDescriptionDetail.text = "Description: ${it.description}"
            }
        }
        return binding.root
    }


}