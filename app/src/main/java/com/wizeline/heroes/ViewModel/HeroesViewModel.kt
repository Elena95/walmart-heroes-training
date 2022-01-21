package com.wizeline.heroes.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroesViewModel(): ViewModel() {
    val resultData= MutableLiveData<List<Result>>()
    //private val heroesAdapter: HeroesAdapter= HeroesAdapter()

    private val repository:Repository=Repository()

    fun getHeroes(offset:Int, limit:Int) {
        repository.getCharacters(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                resultData.value= response.data.results
            }
    }

}