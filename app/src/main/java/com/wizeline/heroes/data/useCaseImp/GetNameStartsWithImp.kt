package com.wizeline.heroes.data.useCaseImp

import com.wizeline.heroes.data.usesCase.GetNameStartsWithUsesCase
import com.wizeline.heroes.repository.Repository
import javax.inject.Inject

class GetNameStartsWithImp @Inject constructor(private val repository: Repository) :
    GetNameStartsWithUsesCase {
    override suspend fun invoke(heroName: String, offset: Int, limit: Int) =
        repository.nameStartsWith(heroName,offset, limit)
}

//Por que heredar y no extender, en kotlin estamos extendiendo por ser una interfaz