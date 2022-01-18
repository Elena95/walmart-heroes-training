package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wizeline.heroes.HeroesAdapter
import com.wizeline.heroes.NetworkClient
import com.wizeline.heroes.databinding.FragmentHeroesBinding


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

        NetworkClient().getCharacters(
            onSuccess = {
                        adapter.submitList(it)
            },
            onError= {
                println(it)

            }
        )
    }


}