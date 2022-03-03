package com.wizeline.heroes.data.useCaseImp

import com.wizeline.heroes.data.usesCase.GetComicsUsesCase
import com.wizeline.heroes.repository.Repository
import javax.inject.Inject
import com.wizeline.heroes.data.entities.Result


class GetComicsUsesCaseImp @Inject constructor(private val repository: Repository) :
    GetComicsUsesCase {
    override fun invoke(heroData: Result) =
        repository.getComics(heroData)
}

