package com.wizeline.heroes

import com.wizeline.heroes.GetSeriesUsesCase
import com.wizeline.heroes.Repository.Repository
import com.wizeline.heroes.Result

class GetSeriesUsesCaseImp(private val repository: Repository) : GetSeriesUsesCase {
    override fun invoke(heroData: Result) =
        repository.getSeries(heroData)
}