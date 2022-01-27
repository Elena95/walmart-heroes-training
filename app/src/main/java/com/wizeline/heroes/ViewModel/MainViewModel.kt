package com.wizeline.heroes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wizeline.heroes.Result

class MainViewModel : ViewModel() {
    private val _destination = MutableLiveData<MyFragments>()
    val destination: LiveData<MyFragments> = _destination

    fun ShowFragmentHeroes(fragment: MyFragments) {
        _destination.value = fragment
    }

}
//En una enu, solo se puede tener un objeto por cada tipo.
//En una clase sealed se pueden tener varios objetos de la misma clase.
/*enum class MyFragments{
    HERO_FRAGMENT,DETAIL_SCREEN_FRAGMENT
}*/

sealed class MyFragments {
    class HeroFragment() : MyFragments()
    class DetailScreenFragment(val dataHero: Result) : MyFragments()
}