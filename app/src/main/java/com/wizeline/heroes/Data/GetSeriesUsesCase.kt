package com.wizeline.heroes

import io.reactivex.rxjava3.core.Single


interface GetSeriesUsesCase {
    operator fun invoke(heroData: Result): Single<SeriesDataWrapper>
}





