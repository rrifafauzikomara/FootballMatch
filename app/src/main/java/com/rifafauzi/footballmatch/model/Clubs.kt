package com.rifafauzi.footballmatch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clubs (val image: Int?, val name: String?, val desc: String?) : Parcelable