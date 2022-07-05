package com.example.evaluateapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluateappfinal.api.RetrofitClient
import com.example.evaluateroom.api.EvaluateApiState
import com.example.evaluateroom.api.Status
import com.example.evaluateroom.model.Evaluate
import com.example.evaluateroom.repository.MainRepository
import com.example.evaluateroom.utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewMoldel : ViewModel() {

    private val repository = MainRepository(
        AppConfig.ApiService()
    )

    val evaluateState = MutableStateFlow(
        EvaluateApiState(
            Status.LOADING,
            listOf<Evaluate>(), ""
        )
    )

    fun getListEvaluate() {
        evaluateState.value = EvaluateApiState.loading()
        viewModelScope.launch {
            repository.getListEvaluate()
                .catch {
                    evaluateState.value = EvaluateApiState.error(it.message.toString())
                }
                .collect {
                    evaluateState.value = EvaluateApiState.success(it.data)
                }
        }
    }

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
        getListEvaluate()
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



