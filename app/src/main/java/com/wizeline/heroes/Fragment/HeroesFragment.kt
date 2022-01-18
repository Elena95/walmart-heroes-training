package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import com.wizeline.heroes.HeroesAdapter
import com.wizeline.heroes.NetworkClient
import com.wizeline.heroes.databinding.FragmentHeroesBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


class HeroesFragment: Fragment(){
    lateinit var binding:FragmentHeroesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    binding= FragmentHeroesBinding.inflate(inflater,container,false)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter=HeroesAdapter()
        binding.mRecyclerView.adapter=adapter

        NetworkClient().getCharacters().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                //Exito
                //binding.mRecyclerView.adapter.submitList(response.data.results)
                adapter.submitList(response.data.results)

            },{
                //ToDo aqui implementar error
                println("ERROR")
            })
    }


}