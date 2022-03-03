package com.wizeline.heroes.data.useCaseImp

import com.wizeline.heroes.data.usesCase.GetHeroesUsesCase
import com.wizeline.heroes.repository.Repository
import javax.inject.Inject
class GetHeroesUsesCaseImp @Inject constructor(private val repository: Repository) :
    GetHeroesUsesCase {
    override fun invoke(offset : Int, limit:Int) =
        repository.getCharacters( offset, limit)

}