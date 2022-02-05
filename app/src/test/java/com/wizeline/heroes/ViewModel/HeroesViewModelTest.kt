package com.wizeline.heroes.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.Common.charactersPOJOModel
import com.wizeline.heroes.GetHeroesUsesCase
import com.wizeline.heroes.Result
import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*


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

}