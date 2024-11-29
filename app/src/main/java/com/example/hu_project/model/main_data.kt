package com.example.hu_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class main_data {

    data class Post(
        val title:String,
        val imageUrl: String
    ) : java.io.Serializable

    data class Comment(val author: String, val content: String)
}