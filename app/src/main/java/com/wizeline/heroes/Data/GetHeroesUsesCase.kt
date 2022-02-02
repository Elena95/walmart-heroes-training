package com.wizeline.heroes

import io.reactivex.rxjava3.core.Single


interface GetHeroesUsesCase {
    operator fun invoke(offset: Int, limit: Int): Single<Characters>
}



