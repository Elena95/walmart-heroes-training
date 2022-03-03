package com.wizeline.heroes.repository

import com.wizeline.heroes.data.entities.Characters
import com.wizeline.heroes.data.entities.Comics
import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.data.entities.SeriesDataWrapper
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getCharacters(offset: Int, limit: Int): Single<Characters>
    fun getComics(heroData: Result): Single<Comics>
    fun getSeries(heroData: Result): Single<SeriesDataWrapper>
   suspend fun nameStartsWith(heroName: String, offset: Int, limit: Int): Characters
}