package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    val getHeroesUsesCase: GetHeroesUsesCase
) : ViewModel() {

    private val _resultData = MutableLiveData<List<Result>>()
    val resultData: LiveData<List<Result>> = _resultData
    var offset = 0;
    private var limit = 5;

    fun getHeroes(offset: Int) {
        getHeroesUsesCase(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _resultData.postValue(response.data.results)
            }, {
                onFailureGetHeroes(it)
            })
    }

    fun onFailureGetHeroes(it: Throwable){
        print("Error $it")
    }
    fun nextPage() {
        offset += limit
        getHeroes(offset)
    }

}