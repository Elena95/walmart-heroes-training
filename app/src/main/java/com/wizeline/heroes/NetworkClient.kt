package com.wizeline.heroes

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Bundle
import io.reactivex.rxjava3.core.Single
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


class NetworkClient() {
    private val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(HeroesServices::class.java)

    fun getCharacters(): Single<Characters> {
        val privateKey = "1e8e0857a86dcbea912ba81bc4e334fbce19c10d"
        val apikey = "4540e962dd0f7a0d1c9816120982d21a"
        val ts = System.currentTimeMillis().toString()
        val hash = (ts + privateKey + apikey).toMD5()
        return api.characters(ts, apikey, hash)
    }
}


