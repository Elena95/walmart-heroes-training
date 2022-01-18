package com.wizeline.heroes

data class Characters(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Result>,
)

data class Result(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics:ComicList
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Url(
    val type: String?,
    val url: String?
)

data class ComicSummary(
    val resourceURI:String?,
    val name:String?
)

data class ComicList(
    val available: Int?,
    val returned: Int?,
    val collectionURI:String?,
    val item:List<ComicSummary>
)

data class StorySummary(
    val resourceURI:String?,
    val name:String?,
    val type: String?,
)

data class StoryList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: Array<StorySummary>
)

data class EventSummary(
    val resourceURI: String?,
    val name: String?,
)

data class EventList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: Array<EventSummary>
)

data class SeriesSummary(
    val resourceURI: String?,
    val name: String?,
)

data class SeriesList(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: Array<SeriesSummary>
)



