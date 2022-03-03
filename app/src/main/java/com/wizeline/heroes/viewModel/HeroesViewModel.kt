package com.wizeline.heroes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.data.usesCase.GetHeroesUsesCase
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
    var offset = 0
    private var limit = 5
    private var listResult = mutableListOf<Result>()
    var idLastHeroList=0
    var idHeroService=0

    fun getHeroes(offset: Int) {
        getHeroesUsesCase(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if(response.data.results.isNotEmpty()){
                    idHeroService = response.data.results[response.data.results.size - 1].id
                }
                if(listResult.size==0||
                    (idLastHeroList!=idHeroService)) {
                    if (response.data.results.isNotEmpty()) {
                        listResult.addAll(response.data.results)
                        _resultData.postValue(listResult)
                    }
                }
            }, {
                onFailureGetHeroes(it)
            })
    }

    fun getListHeroes(){
        if(listResult.size==0) {
        getHeroes(offset)
        }else{
            _resultData.postValue(listResult)
        }
    }

    private fun onFailureGetHeroes(it: Throwable){
        print("Error $it")
    }
    fun nextPage() {
        offset += limit
        getHeroes(offset)
    }

}