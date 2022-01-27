package com.wizeline.heroes

import com.wizeline.heroes.Characters
import com.wizeline.heroes.NetworkClient
import com.wizeline.heroes.toMD5
import io.reactivex.rxjava3.core.Single

//repositoryImplementation, implementa y define los metodos de mi repo
//
class Repository() {
    private val privateKey = "1e8e0857a86dcbea912ba81bc4e334fbce19c10d"
    private val apikey = "4540e962dd0f7a0d1c9816120982d21a"
    private val ts = System.currentTimeMillis().toString()
    private val hash = (ts + privateKey + apikey).toMD5()

    fun getCharacters(offset: Int, limit: Int): Single<Characters> {
        return NetworkClient.api.characters(ts, apikey, hash, offset, limit)
    }

    fun getComics(heroData: Result): Single<Comics> {
        val idHero = heroData.id
        return NetworkClient.api.comics(idHero, ts, apikey, hash)
    }

    fun getSeries(heroData: Result): Single<SeriesDataWrapper> {
        val idHero = heroData.id
        return NetworkClient.api.series(idHero, ts, apikey, hash)
    }
}