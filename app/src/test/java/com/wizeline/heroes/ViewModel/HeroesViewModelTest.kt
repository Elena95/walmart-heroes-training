package com.wizeline.heroes.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wizeline.heroes.Common.charactersPOJOModel
import com.wizeline.heroes.GetHeroesUsesCase
import com.wizeline.heroes.getOrAwaitValue
import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock


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
        val expectedList = charactersPOJOModel()
        viewmodel.getHeroesUsesCase(0,2)
            .test()
            .assertValue(expectedList)
    }

    @Test
    fun isLiveDataEmitting_getOrAwaitValue() {
        val response = charactersPOJOModel()
        viewmodel.getHeroes(0)
        assertEquals(viewmodel.resultData.getOrAwaitValue(), response.data.results)
    }

}