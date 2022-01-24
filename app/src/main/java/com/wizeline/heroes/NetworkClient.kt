package com.wizeline.heroes

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Bundle
import io.reactivex.rxjava3.core.Single
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


object NetworkClient {
    private val BASE_URL = "https://gateway.marvel.com/v1/public/"

         val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(HeroesServices::class.java)

}


