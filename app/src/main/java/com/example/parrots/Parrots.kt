package com.example.parrots

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parrots(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
