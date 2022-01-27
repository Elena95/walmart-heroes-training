package com.wizeline.heroes

import com.wizeline.heroes.Repository.RepositoryImpl
import com.wizeline.heroes.Result

class GetComicsUsesCase() : RepositoryImpl() {
    private var repository = RepositoryImpl()
    override fun getComics(heroData: Result) = repository.getComics(heroData)
}