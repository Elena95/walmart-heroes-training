package com.wizeline.heroes

class Repository {
    fun getCharacters(ts:String,apikey:String, hash:String){
        NetworkClient.api.characters(ts, apikey, hash)
    }
}