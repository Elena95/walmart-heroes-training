package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroesViewModel() : ViewModel() {

    private val _resultData = MutableLiveData<List<Result>>()
    val resultData: LiveData<List<Result>> = _resultData
    private var offset = 0;
    private var limit = 99;
    var currentPage = 0;
    private val usesCases=UsesCases()

    fun getHeroes(offset: Int) {
        usesCases.getCharacters(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _resultData.value = response.data.results
            }, {
                println("ERROR")
            })
    }

    fun nextPage() {
        offset += limit
        getHeroes(offset)
        currentPage++
    }

    fun prevPage() {
        currentPage--
        getHeroes(offset - limit)
        offset -= limit
    }

}