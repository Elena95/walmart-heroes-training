package com.wizeline.heroes

import androidx.databinding.library.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


object NetworkClient {
    //private val BASE_URL = "https://gateway.marvel.com/v1/public/"

    val api = Retrofit.Builder()
        .baseUrl(com.wizeline.heroes.BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(HeroesServices::class.java)

}


