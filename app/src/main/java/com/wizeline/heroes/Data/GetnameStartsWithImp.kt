package com.wizeline.heroes

import com.wizeline.heroes.Repository.Repository
import javax.inject.Inject

class GetnameStartsWithImp @Inject constructor(private val repository: Repository) : GetnameStartsWithUsesCase {
    override suspend fun invoke(heroname: String, offset: Int, limit: Int) =
        repository.nameStartsWith(heroname,offset, limit)
}