package com.wizeline.heroes

import com.wizeline.heroes.Repository.RepositoryImpl

class GetnameStartsWith:RepositoryImpl() {
    private var repository = RepositoryImpl()
    override fun nameStartsWith(heroStart: String) = repository.nameStartsWith(heroStart)

}