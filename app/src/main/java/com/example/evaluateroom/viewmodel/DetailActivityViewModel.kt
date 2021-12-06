package com.example.evaluateappfinal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evaluateappfinal.api.RetrofitClient
import com.example.evaluateroom.model.Evaluate
import com.example.myapplication.repository.GetEvaluateRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel(evaluate: Evaluate) : ViewModel() {
    private val TAG = "DetailActivityViewModel"


    var _evaluate = MutableLiveData<Evaluate>()
    val evaluate: LiveData<Evaluate>
        get() = _evaluate

    init {
        getApiEvaluateById(evaluate.id)
    }


//    fun getItemById(id: Int): MutableLiveData<Evaluate> {
//        _evaluate = GetEvaluateRepository.getApiEvaluateById(id)
//        return _evaluate
//    }

    private fun getApiEvaluateById(id: Int) {
        RetrofitClient.retrofitApi.getEvaluate(id).enqueue(object : Callback<Evaluate> {
            override fun onResponse(call: Call<Evaluate>, response: Response<Evaluate>) {
                _evaluate.value = response.body()
            }

            override fun onFailure(call: Call<Evaluate>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }

        })
    }


}





