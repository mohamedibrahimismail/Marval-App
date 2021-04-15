package com.example.marval.model.main

data class Result(
    val comics: Comics,
    val description: String,
    val events: Comics,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Comics,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)