package com.example.newsfresh

data class News (
    val title: String,
    val source: Source,
    val url: String,
    val imageUrl:String,
) {
    data class Source(
        val name: String,
    )}


