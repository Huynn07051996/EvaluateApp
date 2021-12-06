package com.example.evaluateroom.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "evaluate_table")
@Parcelize
data class EvaluateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val price: Int,
    val tille: String,
    val description: String
) : Parcelable