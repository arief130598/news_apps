package com.arief.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var source: Source = Source(),
    var authour: String = "",
    var title: String = "",
    var description: String = "",
    var url: String = "",
    var urlToImage: String? = "",
    var publishedAt: String = "",
    var content: String? = "",
) : Parcelable
