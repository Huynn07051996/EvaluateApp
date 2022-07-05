package com.example.evaluateroom.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.evaluateroom.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapter {

    companion object {

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(floatingActionButton: FloatingActionButton, navigate: Boolean) {
            floatingActionButton.setOnClickListener {
                if (navigate) {
                    floatingActionButton.findNavController()
                        .navigate(R.id.action_homeFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyData")
        @JvmStatic
        fun emptyData(view: View, emptyData: MutableLiveData<Boolean>) {
            when (emptyData.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:emptyDataDetailFragment")
        @JvmStatic
        fun emptyDataDetailFragment(view: View, emptyData: MutableLiveData<Boolean>) {
            when (emptyData.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, urlImage: String) {
            Glide.with(imageView).load(urlImage).into(imageView)
        }


    }
}