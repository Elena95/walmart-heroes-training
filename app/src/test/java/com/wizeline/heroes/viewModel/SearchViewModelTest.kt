package com.wizeline.heroes.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wizeline.heroes.Common.*
import com.wizeline.heroes.data.usesCase.GetNameStartsWithUsesCase
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel

    private val searchUseCase = mock(GetNameStartsWithUsesCase::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        searchViewModel = SearchViewModel(searchUseCase)
    }


    @Test
    fun fetch_search_characters_with_offset_0() = coroutineDispatcher.runBlockingTest {
        searchViewModel.nameStartsWith("3",0,5)
        coroutineDispatcher.advanceUntilIdle()
        val data = searchViewModel.resultData.value
        Assert.assertEquals(
            searchPOJO().data.results,
            data
        )
    }

    @After
    fun shutDown() {
        Dispatchers.resetMain()
    }
}


