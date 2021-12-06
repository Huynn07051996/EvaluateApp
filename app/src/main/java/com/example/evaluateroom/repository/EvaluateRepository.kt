package com.example.evaluateappfinal.repository

import androidx.lifecycle.LiveData
import com.example.evaluateappfinal.data.EvaluateDao
import com.example.evaluateroom.model.EvaluateEntity

class EvaluateRepository(private val evaluateDao: EvaluateDao) {

    val readAllData: LiveData<List<EvaluateEntity>> = evaluateDao.readAllData()

    suspend fun addEvaluate(evaluateEntity: EvaluateEntity) {
        evaluateDao.addEvaluate(evaluateEntity)
    }
}