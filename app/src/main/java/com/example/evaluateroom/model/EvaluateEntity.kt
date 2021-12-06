package com.example.evaluateroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "evaluate_table")
data class EvaluateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val price: Int,
    val tille: String,
    val description: String
)
