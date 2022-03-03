package com.wizeline.heroes.data.entities

data class Comics(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataComics
)

data class DataComics(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
)

data class Comic(
    val id: Int?,
    val title: String,
    val thumbnail: Thumbnail,
    val description: String
)


