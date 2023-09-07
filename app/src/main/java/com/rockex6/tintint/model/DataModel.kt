package com.rockex6.tintint.model

data class DataModel(
    val albumId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = "",
    val type: Int = 0
) {
    constructor(type: Int) : this(0, 0, "", "", "", type)
}