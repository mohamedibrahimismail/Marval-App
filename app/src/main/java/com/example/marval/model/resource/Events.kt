package com.example.marval.model.resource

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)