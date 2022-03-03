package com.wizeline.heroes.data.usesCase


import com.wizeline.heroes.data.entities.Comics
import com.wizeline.heroes.data.entities.Result
import io.reactivex.rxjava3.core.Single

interface GetComicsUsesCase {
    operator fun invoke(heroData: Result): Single<Comics>
}