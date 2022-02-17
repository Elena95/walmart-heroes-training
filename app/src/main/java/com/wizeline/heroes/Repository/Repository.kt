package com.wizeline.heroes.Repository

import com.wizeline.heroes.Characters
import com.wizeline.heroes.Comics
import com.wizeline.heroes.Result
import com.wizeline.heroes.SeriesDataWrapper
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getCharacters(offset: Int, limit: Int): Single<Characters>
    fun getComics(heroData: Result): Single<Comics>
    fun getSeries(heroData: Result): Single<SeriesDataWrapper>
   suspend fun nameStartsWith(heroName: String, offset: Int, limit: Int): Characters
}