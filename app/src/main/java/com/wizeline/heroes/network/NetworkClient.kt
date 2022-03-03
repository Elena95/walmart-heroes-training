package com.wizeline.heroes.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient{
    private val  api = Retrofit.Builder()
        .baseUrl(com.wizeline.heroes.BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun getClientJavaRX(): HeroesServices {
        return api
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(HeroesServices::class.java)
    }

    fun getClientCorrutinas(): HeroesServicesCorrutina {
        return api
            .build()
            .create(HeroesServicesCorrutina::class.java)
    }
}