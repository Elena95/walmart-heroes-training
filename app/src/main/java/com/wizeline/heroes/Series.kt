package com.wizeline.heroes

data class Series(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: SeriesData

    )

data class SeriesData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<SeriesList>
)

data class StorySummary(
    val resourceURI:String?,
    val name:String?,
    val type: String?,
)

data class SeriesSummary(
    val resourceURI: String?,
    val name: String?,
)

data class SeriesList(
    val id: Int?,
    val title:String,
    val thumbnail: Thumbnail,
    val description: String

)
