package com.example.evaluateappfinal.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evaluateroom.model.EvaluateEntity

@Dao
interface EvaluateDao {

    @Query("SELECT * FROM evaluate_table ORDER by id ASC")
    fun readAllData(): LiveData<List<EvaluateEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvaluate(evaluateEntity: EvaluateEntity)
}