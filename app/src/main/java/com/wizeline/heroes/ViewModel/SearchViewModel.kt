package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.heroes.GetnameStartsWithUsesCase
import com.wizeline.heroes.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val nameStartsWith: GetnameStartsWithUsesCase) :
    ViewModel() {
    private val _resultData = MutableLiveData<List<Result>>()
    val resultData: LiveData<List<Result>> = _resultData
    var offset = 0
    private var limit = 5

    fun searchHeroes(nameStart: String, offset: Int) {
        viewModelScope.launch {
            val result = nameStartsWith(nameStart, offset, limit)
            _resultData.postValue(result.data.results)
        }
    }

    fun nextPage(nameStart: String) {
        offset += limit
        searchHeroes(nameStart, offset)
    }
    fun startPagin(){
        _resultData.postValue(emptyList())
    }
}