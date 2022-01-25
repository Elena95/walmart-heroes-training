package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HeroesViewModel(): ViewModel() {
   /* private*/ val resultData = MutableLiveData<List<Result>>()

    private val repository: Repository = Repository()

    //getCharacters
// LiveData, por buena practica debe ser private(mutableData), y retorna resultData

    //second: Capa entre repositorios y viewModel  llamada USECASE,  carpeta de casos de uso
    fun getHeroes(offset:Int)/*: List<Result>*/ {
        var limit=5
        repository.getCharacters(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                resultData.value= response.data.results
            }
       // return resultData.value!!
    }

}