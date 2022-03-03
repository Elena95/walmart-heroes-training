package com.wizeline.heroes.repository

import com.wizeline.heroes.*
import com.wizeline.heroes.data.entities.Characters
import com.wizeline.heroes.data.entities.Comics
import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.data.entities.SeriesDataWrapper
import com.wizeline.heroes.data.entities.toMD5
import com.wizeline.heroes.network.HeroesServices
import com.wizeline.heroes.network.HeroesServicesCorrutina
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class RepositoryImpl @Inject constructor(private val api : HeroesServices, private val apiC: HeroesServicesCorrutina) : Repository {

    private val ts = System.currentTimeMillis().toString()
    private val hash = (ts + BuildConfig.PRIVATKEY + BuildConfig.APIKEY).toMD5()

    override fun getCharacters(offset: Int, limit: Int): Single<Characters> {
        return api.characters(ts, BuildConfig.APIKEY, hash, offset, limit)
    }

    override fun getComics(heroData: Result): Single<Comics> {
        val idHero = heroData.id
        return api.comics(idHero, ts, BuildConfig.APIKEY, hash)
    }

    override fun getSeries(heroData: Result): Single<SeriesDataWrapper> {
        val idHero = heroData.id
        return api.series(idHero, ts, BuildConfig.APIKEY, hash)
    }

    override suspend fun nameStartsWith(heroName: String, offset: Int, limit: Int): Characters =
        apiC.nameStartsWith(ts, BuildConfig.APIKEY, hash, heroName, offset, limit)

}
