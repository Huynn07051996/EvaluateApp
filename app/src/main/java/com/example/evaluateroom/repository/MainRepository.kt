package com.example.evaluateroom.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.evaluateapp.api.RetrofitService
import com.example.evaluateroom.api.EvaluateApiState
import com.example.evaluateroom.model.Evaluate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val retrofitService: RetrofitService) {

    /***
     * Function which will call the api and it will return a Flow.
     * Return a flow, It asynchronously performs calculation or function
     * and the emit function emits the data to the receivers which are listening
     * to this flow.
     */
    suspend fun getListEvaluate(): Flow<EvaluateApiState<List<Evaluate>>> {
        return flow {
            //get the evaluate Data from the api
            val listEvaluate = retrofitService.getDataFormApi2()
            Log.e(TAG, "$listEvaluate")

//            Emit this data wrapped in the helper class [EvaluateApiState]
            emit(EvaluateApiState.success(listEvaluate))
        }.flowOn(Dispatchers.IO)
    }
}