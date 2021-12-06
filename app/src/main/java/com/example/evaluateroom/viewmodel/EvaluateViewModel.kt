package com.example.evaluateappfinal.viewmodel

import android.app.Application
import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.evaluateappfinal.data.EvaluateDatabase
import com.example.evaluateappfinal.repository.EvaluateRepository
import com.example.evaluateroom.model.EvaluateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EvaluateViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<EvaluateEntity>>
    private val repository: EvaluateRepository

    init {
        val evaluateDao = EvaluateDatabase.getDatabase(application).evaluateDao()
        repository = EvaluateRepository(evaluateDao)
        readAllData = repository.readAllData
    }

    fun addEvaluate(evaluateEntity: EvaluateEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEvaluate(evaluateEntity)
        }
    }

    fun updateData(evaluateEntity: EvaluateEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(evaluateEntity)
        }
    }

    fun inputCheck(
        urlImage: String,
        price: Editable,
        title: String,
        description: String
    ): Boolean {
        return !(TextUtils.isEmpty(urlImage) && price.isEmpty() && TextUtils.isEmpty(title) && TextUtils.isEmpty(
            description
        ))
    }
}