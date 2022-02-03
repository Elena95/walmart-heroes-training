package com.wizeline.heroes

import com.wizeline.heroes.Characters
import com.wizeline.heroes.Result
import io.reactivex.rxjava3.core.Single

interface GetnameStartsWithUsesCase {
    suspend operator fun invoke(heroname: String, offset: Int, limit: Int): Characters

}
