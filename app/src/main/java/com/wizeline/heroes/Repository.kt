package com.wizeline.heroes

import retrofit2.Call


class Repository {
    fun getCharacters(ts:String,apikey:String, hash:String):Call<Characters>{
         return NetworkClient.api.characters(ts, apikey, hash)
    }
}