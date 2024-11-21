package com.example.hu_project.model

class main_data {
    data class Post(val title:String, val imageUrl: String)
    data class Posting(
        val category: String,
        val content: String,
        val owner: String
    )
}