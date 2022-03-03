package com.wizeline.heroes.data.entities

import java.io.Serializable

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
    val comics: ComicList
):Serializable

data class Thumbnail(
    val path: String,
    val extension: String
)

data class ComicList(
    val available: Int?,
    val collectionURI: String?,
)



