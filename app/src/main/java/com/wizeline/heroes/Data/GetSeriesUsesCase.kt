package com.wizeline.heroes

import com.wizeline.heroes.Repository.RepositoryImpl

class GetSeriesUsesCase : RepositoryImpl() {
    private var repository = RepositoryImpl()
    override fun getSeries(heroData: Result) = repository.getSeries(heroData)
}