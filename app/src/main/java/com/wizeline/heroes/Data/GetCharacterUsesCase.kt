package com.wizeline.heroes

import com.wizeline.heroes.Repository.RepositoryImpl

class GetCharacterUsesCase() : RepositoryImpl() {
    private var repository = RepositoryImpl()
    override fun getCharacters(offset: Int, limit: Int) = repository.getCharacters(offset, limit)
}