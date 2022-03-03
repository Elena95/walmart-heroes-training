package com.wizeline.heroes.data.usesCase

import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.data.entities.SeriesDataWrapper
import io.reactivex.rxjava3.core.Single


interface GetSeriesUsesCase {
    operator fun invoke(heroData: Result): Single<SeriesDataWrapper>
}





