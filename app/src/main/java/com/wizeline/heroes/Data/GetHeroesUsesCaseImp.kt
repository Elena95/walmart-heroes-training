package com.wizeline.heroes

import com.wizeline.heroes.Repository.Repository
import javax.inject.Inject
class GetHeroesUsesCaseImp @Inject constructor(private val repository: Repository) : GetHeroesUsesCase {
    override fun invoke(offset : Int, limit:Int) =
        repository.getCharacters( offset, limit)

}