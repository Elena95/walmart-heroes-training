package com.wizeline.heroes.Repository

import com.wizeline.heroes.*
import com.wizeline.heroes.Network.HeroesServices
import com.wizeline.heroes.Network.HeroesServicesCorrutina
import com.wizeline.heroes.Utils.APIKEY
import com.wizeline.heroes.Utils.PRIVATEKEY
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class RepositoryImpl @Inject constructor(private val api : HeroesServices, private val apiC: HeroesServicesCorrutina) : Repository {

    private val ts = System.currentTimeMillis().toString()
    private val hash = (ts + PRIVATEKEY + APIKEY).toMD5()

    override fun getCharacters(offset: Int, limit: Int): Single<Characters> {
        return api.characters(ts, APIKEY, hash, offset, limit)
    }

    override fun getComics(heroData: Result): Single<Comics> {
        val idHero = heroData.id
        return api.comics(idHero, ts, APIKEY, hash)
    }

    override fun getSeries(heroData: Result): Single<SeriesDataWrapper> {
        val idHero = heroData.id
        return api.series(idHero, ts, APIKEY, hash)
    }

    override suspend fun nameStartsWith(heroname: String, offset: Int, limit: Int): Characters =
        apiC.nameStartsWith(ts, APIKEY, hash, heroname, offset, limit)


    // override fun getCharacters(offset: Int, limit: Int): Single<Characters> = api.characters(ts, apikey, hash, offset, limit)

}