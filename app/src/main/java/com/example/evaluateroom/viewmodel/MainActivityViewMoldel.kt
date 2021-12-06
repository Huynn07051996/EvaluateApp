package com.example.evaluateapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evaluateappfinal.api.RetrofitClient
import com.example.evaluateroom.model.Evaluate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewMoldel : ViewModel() {
    private val TAG = "MainActivityViewMoldel"

    val emptyData: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkDataEmpty(data: List<Evaluate>){
        emptyData.value = data.isEmpty()
    }


    private val _respone = MutableLiveData<List<Evaluate>>()
    val respone: LiveData<List<Evaluate>>
        get() = _respone

    init {
        getApiRespone()
    }

    private fun getApiRespone() {
        RetrofitClient.retrofitApi.getDataFormApi().enqueue(object : Callback<List<Evaluate>> {
            override fun onResponse(
                call: Call<List<Evaluate>>,
                response: Response<List<Evaluate>>
            ) {
                _respone.value = response.body()
                Log.e(TAG, "onResponse: ", )
            }
            override fun onFailure(call: Call<List<Evaluate>>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })

    }



}



