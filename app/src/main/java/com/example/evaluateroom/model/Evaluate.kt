package com.example.evaluateroom.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Evaluate(
    val id: Int? = null,
    val title: String? = null,
    val price: Float? = null,
    val description: String? = null,
    val image: String? = null,
    val rating: Rate? = null
) : Parcelable

@Parcelize
data class Rate(
    val rate: Float? = null
) : Parcelable

