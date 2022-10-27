package com.arief.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source (
    var id: String? = "",
    var name: String = ""
) : Parcelable