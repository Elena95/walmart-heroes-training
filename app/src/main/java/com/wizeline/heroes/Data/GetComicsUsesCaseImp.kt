package com.wizeline.heroes

import com.wizeline.heroes.Repository.Repository

class GetComicsUsesCaseImp(private val repository: Repository) : GetComicsUsesCase {
    override fun invoke(heroData: Result) =
        repository.getComics(heroData)
}
