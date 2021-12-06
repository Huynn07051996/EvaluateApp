package com.example.evaluateroom.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.evaluateappfinal.viewmodel.EvaluateViewModel
import com.example.evaluateroom.R
import com.example.evaluateroom.databinding.FragmentAddBinding
import com.example.evaluateroom.model.EvaluateEntity


class AddFragment : Fragment() {

    private val viewModel: EvaluateViewModel by viewModels()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add, container, false)
        //Binding Data
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
        binding.btnListDatabase.setOnClickListener {
            val action: NavDirections =
                AddFragmentDirections.actionAddFragmentToListRoomDatabaseFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val urlImage = binding.edtUrlImage.text.toString()
        val price = binding.edtPrice.text
        val title = binding.edtTitle.text.toString()
        val description = binding.edtDescription.text.toString()

        if (viewModel.inputCheck(urlImage, price, title, description)) {
            //create EvaluateEntity object
            val evaluateEntity = EvaluateEntity(
                0,
                urlImage,
                Integer.parseInt(price.toString()),
                title,
                description
            )
            //add data to database
            viewModel.addEvaluate(evaluateEntity)
            Toast.makeText(requireContext(), "Successfully added.", Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listRoomDatabaseFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

}