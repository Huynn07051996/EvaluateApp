package com.example.evaluateroom.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Evaluate(
    val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val image: String,
    val rating: Rate
) : Parcelable

@Parcelize
data class Rate(
    val rate: Float
) : Parcelable

