package com.wizeline.heroes.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wizeline.heroes.data.entities.Characters
import com.wizeline.heroes.Common.charactersPOJOModel
import com.wizeline.heroes.data.usesCase.GetHeroesUsesCase
import com.wizeline.heroes.getOrAwaitValue
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import junit.framework.TestCase
import net.bytebuddy.matcher.ElementMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock


lateinit var client: GetHeroesUsesCase

lateinit var underTest: HeroesViewModel

@RunWith(JUnit4::class)
class HeroesViewModelTest : TestCase() {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    val getHeroesUsesCase = mock(GetHeroesUsesCase::class.java)
    private val viewmodel by lazy { HeroesViewModel(getHeroesUsesCase) }

    @Test
    fun testHeroesUseCases_updateResultData() {
        val response = charactersPOJOModel()
        Mockito.`when`(viewmodel.getHeroesUsesCase(anyInt(),anyInt()))
            .thenReturn(Single.just(response))
        viewmodel.getHeroesUsesCase(0,2)
            .test()
    }

    @Test
    fun isLiveDataEmitting_getOrAwaitValue() {
        val response = charactersPOJOModel()
        viewmodel.getHeroes(0)
        assertEquals(viewmodel.resultData.getOrAwaitValue(), response.data.results)
    }
}


