package com.wizeline.heroes

import com.wizeline.heroes.Repository.Repository

class GetHeroesUsesCaseImp(private val repository: Repository) : GetHeroesUsesCase {
    override fun invoke(offset : Int, limit:Int) =
        repository.getCharacters( offset, limit)
}