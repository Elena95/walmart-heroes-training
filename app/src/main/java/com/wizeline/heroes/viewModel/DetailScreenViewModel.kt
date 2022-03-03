package com.wizeline.heroes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.data.entities.Comic
import com.wizeline.heroes.data.entities.Series
import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.data.usesCase.GetComicsUsesCase
import com.wizeline.heroes.data.usesCase.GetSeriesUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getComicsUsesCase: GetComicsUsesCase,
    private val getSeriesUsesCase: GetSeriesUsesCase
) : ViewModel() {

    private val _comicsData = MutableLiveData<List<Comic>>()
    val comicsData: LiveData<List<Comic>> = _comicsData
    private val _seriesData = MutableLiveData<List<Series>>()
    val seriesData: LiveData<List<Series>> = _seriesData

    fun getComics(idHero: Result) {
        getComicsUsesCase(idHero)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap({
                getSeriesUsesCase(idHero)
            }, { comicsResult, seriesResult ->
                Pair(comicsResult, seriesResult)
            })
            .subscribe { result ->
                _comicsData.postValue(result.first.data.results)
                _seriesData.postValue(result.second.data.results)
            }

    }

}
