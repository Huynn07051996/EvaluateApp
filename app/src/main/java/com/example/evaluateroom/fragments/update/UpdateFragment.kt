package com.example.evaluateroom.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.evaluateappfinal.viewmodel.EvaluateViewModel
import com.example.evaluateroom.R
import com.example.evaluateroom.databinding.FragmentUpdateBinding
import com.example.evaluateroom.model.EvaluateEntity


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val viewModel: EvaluateViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.edtUrlImage.setText(args.evaluateEntity.image)
        binding.edtPrice.setText(args.evaluateEntity.price.toString())
        binding.edtTitle.setText(args.evaluateEntity.tille)
        binding.edtDescription.setText(args.evaluateEntity.description)

        binding.btnUpdate.setOnClickListener {
            val urlImage = binding.edtUrlImage.text.toString()
            val price = binding.edtPrice.text
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDescription.text.toString()

            val validation = viewModel.inputCheck(urlImage, price, title, description)
            if (validation) {
                val evaluateEntity = EvaluateEntity(
                    args.evaluateEntity.id,
                    urlImage,
                    Integer.parseInt(price.toString()),
                    title,
                    description
                )

                viewModel.updateData(evaluateEntity)
                Toast.makeText(requireContext(), "Successfully updated.", Toast.LENGTH_SHORT).show()
                //navigate back
                findNavController().navigate(R.id.action_updateFragment_to_listRoomDatabaseFragment)
            }else{
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        return binding.root
    }

}