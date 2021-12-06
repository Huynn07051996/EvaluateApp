package com.example.evaluateappfinal.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.evaluateroom.model.EvaluateEntity

@Dao
interface EvaluateDao {

    @Query("SELECT * FROM evaluate_table ORDER by id ASC")
    fun readAllData(): LiveData<List<EvaluateEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvaluate(evaluateEntity: EvaluateEntity)

    @Update
    suspend fun updateData(evaluateEntity: EvaluateEntity)
}