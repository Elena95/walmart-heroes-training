package com.wizeline.heroes


interface GetnameStartsWithUsesCase {
    suspend operator fun invoke(heroname: String, offset: Int, limit: Int): Characters

}
