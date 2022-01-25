package com.wizeline.heroes

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesServices {
    @GET("characters")
    fun characters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Single<Characters>

    @GET("characters/{id}/comics")
fun comics(
        @Path("id")id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
): Single<Comics>
    @GET("characters/{id}/series")
    fun series(
        @Path("id")id: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Single<Series>
}
