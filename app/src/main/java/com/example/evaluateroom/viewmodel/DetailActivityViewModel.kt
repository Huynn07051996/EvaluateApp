package com.example.evaluateroom.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evaluateappfinal.api.RetrofitClient
import com.example.evaluateroom.model.Evaluate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel(evaluate: Evaluate) : ViewModel() {
    private val TAG = "DetailActivityViewModel"


    var _evaluate = MutableLiveData<Evaluate>()
    val evaluate: LiveData<Evaluate>
        get() = _evaluate

    init {
        evaluate.id?.let { getApiEvaluateById(it) }
    }

    var emptyDataDetailFragment: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkDataEmpty(evaluate: Evaluate?){
        emptyDataDetailFragment.value = evaluate == null
    }

    private fun getApiEvaluateById(id: Int) {
        RetrofitClient.retrofitApi.getEvaluate(id).enqueue(object : Callback<Evaluate> {
            override fun onResponse(call: Call<Evaluate>, response: Response<Evaluate>) {
                _evaluate.value = response.body()
                checkDataEmpty(_evaluate.value)
            }

            override fun onFailure(call: Call<Evaluate>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }

        })
    }


}





