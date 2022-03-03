package com.wizeline.heroes.data.entities

data class SeriesDataWrapper(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: SeriesDataContainer

)

data class SeriesDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Series>
)


data class Series(
    val id: Int?,
    val title: String,
    val thumbnail: Thumbnail,
    val description: String

)
