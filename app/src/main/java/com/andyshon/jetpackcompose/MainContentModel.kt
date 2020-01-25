package com.andyshon.jetpackcompose

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainContentModel(val title: String, val image: Int): Parcelable