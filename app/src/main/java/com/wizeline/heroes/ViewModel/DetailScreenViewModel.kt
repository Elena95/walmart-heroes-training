package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.Comic
import com.wizeline.heroes.Repository
import com.wizeline.heroes.Result
import com.wizeline.heroes.Series
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class DetailScreenViewModel() : ViewModel() {
    private val _comicsData = MutableLiveData<List<Comic>>()
    val comicsData: LiveData<List<Comic>> = _comicsData
    private val _seriesData = MutableLiveData<List<Series>>()
    val seriesData: LiveData<List<Series>> = _seriesData
    private val repository: Repository = Repository()

    fun getComics(idHero: Result) {
        repository.getComics(idHero)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap({
                repository.getSeries(idHero)
            }, { comicsResult, seriesResult ->
                Pair(comicsResult, seriesResult)
            })
            .subscribe { result ->
                _comicsData.postValue(result.first.data.results)
                _seriesData.postValue(result.second.data.results)
            }

    }

}
