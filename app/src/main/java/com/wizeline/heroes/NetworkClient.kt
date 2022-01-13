package com.wizeline.heroes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
            var BASE_URL = "https://gateway.marvel.com/v1/public/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /*fun getServices(): HeroesServices {
        return Retrofit.Builder().apply {
            baseUrl("https://gateway.marvel.com/v1/public/")
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(HeroesServices::class.java)
    }*/

    //val api = retrofit.create<HeroesServices>()
      val api = retrofit.create(HeroesServices::class.java)

    }


