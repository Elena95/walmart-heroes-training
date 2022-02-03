package com.wizeline.heroes.Network

import com.wizeline.heroes.Characters
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesServicesCorrutina {

    @GET("characters")
    //esa funcion tiene que correr forzozamente en otro hilo (hilo de una corrutina)
    suspend fun nameStartsWith(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith")name:String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Characters
}