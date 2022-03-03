package com.wizeline.heroes.data.useCaseImp

import com.wizeline.heroes.data.usesCase.GetSeriesUsesCase
import com.wizeline.heroes.repository.Repository
import com.wizeline.heroes.data.entities.Result
import javax.inject.Inject

class GetSeriesUsesCaseImp @Inject constructor(private val repository: Repository) :
    GetSeriesUsesCase {
    override fun invoke(heroData: Result) =
        repository.getSeries(heroData)
}