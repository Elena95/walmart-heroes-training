package com.wizeline.heroes

import com.wizeline.heroes.Repository.Repository
import javax.inject.Inject

class GetSeriesUsesCaseImp @Inject constructor(private val repository: Repository) : GetSeriesUsesCase {
    override fun invoke(heroData: Result) =
        repository.getSeries(heroData)
}