package com.wizeline.heroes.viewModel

import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.data.usesCase.GetNameStartsWithUsesCase
import com.wizeline.heroes.data.entities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val nameStartsWith: GetNameStartsWithUsesCase) :
    ViewModel() {
    private val _resultData = MutableLiveData<List<Result>>()
    val resultData: LiveData<List<Result>> = _resultData
    var offset = 0
    private var limit = 5
    private var listResult = mutableListOf<Result>()
    var idLastHeroList=0
    var idHeroService=0


    fun searchHeroes(nameStart: String, offset: Int) {
        viewModelScope.launch {
            val result = nameStartsWith(nameStart, offset, limit)
            if(result.data.results.isNotEmpty()) {
                idHeroService = result.data.results[result.data.results.size - 1].id
                if (listResult.size == 0 ||
                    (idLastHeroList != idHeroService)
                ) {
                    listResult.addAll(result.data.results)
                    _resultData.postValue(listResult)
                }
            }
        }
    }

    fun getListSearch(nameStart: String){
        if(listResult.size==0) {
            searchHeroes(nameStart, offset)
        }else{
            _resultData.postValue(listResult)
        }
    }

    fun nextPage(nameStart: String) {
        offset += limit
        searchHeroes(nameStart, offset)
    }
    fun startPage(){
        _resultData.postValue(emptyList())
    }
}