package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evaluateappfinal.api.RetrofitClient
import com.example.evaluateroom.model.Evaluate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GetEvaluateRepository {

    var _mListEvaluate = MutableLiveData<List<Evaluate>>()
    val mListEvaluate: LiveData<List<Evaluate>>
        get() = _mListEvaluate

    var _mEvaluate = MutableLiveData<Evaluate>()
    val mEvaluate: LiveData<Evaluate>
        get() = _mEvaluate


    private val TAG = "GetEvaluateRepository"

    fun getApiRespone(): MutableLiveData<List<Evaluate>> {
        val call = RetrofitClient.retrofitApi.getDataFormApi()
        call.enqueue(object : Callback<List<Evaluate>> {
            override fun onResponse(
                call: Call<List<Evaluate>>,
                response: Response<List<Evaluate>>
            ) {
                _mListEvaluate.value = response.body()
            }

            override fun onFailure(call: Call<List<Evaluate>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

        return _mListEvaluate
    }

    fun getApiEvaluateById(id: Int): MutableLiveData<Evaluate> {
        val call = RetrofitClient.retrofitApi.getEvaluate(id)
        call.enqueue(object : Callback<Evaluate> {
            override fun onResponse(call: Call<Evaluate>, response: Response<Evaluate>) {
                _mEvaluate.value = response.body()
            }

            override fun onFailure(call: Call<Evaluate>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
        return _mEvaluate

    }



}