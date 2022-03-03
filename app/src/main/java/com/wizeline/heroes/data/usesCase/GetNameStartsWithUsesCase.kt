package com.wizeline.heroes.data.usesCase

import com.wizeline.heroes.data.entities.Characters


interface GetNameStartsWithUsesCase {
    suspend operator fun invoke(heroName: String, offset: Int, limit: Int): Characters

}
