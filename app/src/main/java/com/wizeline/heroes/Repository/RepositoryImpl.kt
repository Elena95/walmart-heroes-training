package com.wizeline.heroes.Repository

import com.wizeline.heroes.*
import com.wizeline.heroes.NetworkClient.api
import io.reactivex.rxjava3.core.Single

open class RepositoryImpl() : Repository {
    private val privateKey = "1e8e0857a86dcbea912ba81bc4e334fbce19c10d"
    private val apikey = "4540e962dd0f7a0d1c9816120982d21a"
    private val ts = System.currentTimeMillis().toString()
    private val hash = (ts + privateKey + apikey).toMD5()

    override fun getCharacters(offset:Int, limit: Int): Single<Characters> {
        return api.characters(ts, apikey, hash, offset, limit)
    }

    override fun getComics(heroData: Result): Single<Comics> {
        val idHero = heroData.id
        return api.comics(idHero, ts, apikey, hash)
    }

    override fun getSeries(heroData: Result): Single<SeriesDataWrapper> {
        val idHero = heroData.id
        return api.series(idHero, ts, apikey, hash)
    }

    override fun nameStartsWith(heroname: String):Single<Characters> = api.nameStartsWith(ts, apikey, hash, heroname)


    // override fun getCharacters(offset: Int, limit: Int): Single<Characters> = api.characters(ts, apikey, hash, offset, limit)

}