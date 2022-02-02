package com.wizeline.heroes


import io.reactivex.rxjava3.core.Single

interface GetComicsUsesCase {
    operator fun invoke(heroData: Result): Single<Comics>
}