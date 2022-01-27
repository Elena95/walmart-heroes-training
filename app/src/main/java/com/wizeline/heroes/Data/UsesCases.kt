package com.wizeline.heroes

import com.wizeline.heroes.Repository.RepositoryImpl

class UsesCases() : RepositoryImpl() {
    private var repository = RepositoryImpl()
    override fun getCharacters(offset: Int, limit: Int) = repository.getCharacters(offset, limit)
    override fun getComics(heroData: Result) = repository.getComics(heroData)
    override fun getSeries(heroData: Result) = repository.getSeries(heroData)

}