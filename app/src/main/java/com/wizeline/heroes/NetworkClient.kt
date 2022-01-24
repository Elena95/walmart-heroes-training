package com.wizeline.heroes

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Bundle


class NetworkClient() {
    private val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HeroesServices::class.java)

    fun getCharacters(onSuccess: (List<Result>?) -> Unit, onError: (Throwable) -> Unit) {
        val privateKey = "244be7c2496cbf6d331145cc489b4892457cc2c0"
        val apikey = "6ffcf49b680b7250a6983acd33731f55"
        val ts = System.currentTimeMillis().toString()
        val hash = (ts + privateKey + apikey).toMD5()
        val response = api.characters(ts, apikey, hash)
        response.enqueue(object : retrofit2.Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                onSuccess(response.body()?.data?.results)
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                onError(t)
            }
        })
    }
}


