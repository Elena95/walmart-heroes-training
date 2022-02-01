package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.GetnameStartsWith
import com.wizeline.heroes.Result
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel : ViewModel() {
    private val _resultData = MutableLiveData<List<Result>>()
    val resultData: LiveData<List<Result>> = _resultData
    private val usesCase = GetnameStartsWith()
    var offset = 0;
    private var limit = 5;

    fun searchHeroes(nameStart: String, offset: Int) {
        usesCase.nameStartsWith(nameStart, offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                _resultData.postValue(response.data.results)
            },
                {
                    print("Error")
                })
    }

    fun nextPage(nameStart: String) {
        offset += limit
        searchHeroes(nameStart, offset)
    }
}