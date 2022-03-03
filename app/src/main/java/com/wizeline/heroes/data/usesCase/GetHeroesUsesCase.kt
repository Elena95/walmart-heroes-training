package com.wizeline.heroes.data.usesCase

import com.wizeline.heroes.data.entities.Characters
import io.reactivex.rxjava3.core.Single

interface GetHeroesUsesCase {
    operator fun invoke(offset: Int, limit: Int): Single<Characters>
}



