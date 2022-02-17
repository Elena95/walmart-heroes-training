package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.*
import com.wizeline.heroes.GetComicsUsesCase
import com.wizeline.heroes.GetSeriesUsesCase
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
