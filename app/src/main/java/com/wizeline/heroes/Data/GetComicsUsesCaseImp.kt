package com.wizeline.heroes

import com.wizeline.heroes.Repository.Repository
import javax.inject.Inject

class GetComicsUsesCaseImp @Inject constructor(private val repository: Repository) : GetComicsUsesCase {
    override fun invoke(heroData: Result) =
        repository.getComics(heroData)
}

