package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wizeline.heroes.HeroesAdapter
import com.wizeline.heroes.ViewModel.HeroesViewModel
import com.wizeline.heroes.databinding.FragmentHeroesBinding

class HeroesFragment : Fragment() {
    lateinit var binding: FragmentHeroesBinding
    private val viewModel: HeroesViewModel = HeroesViewModel()
    private val heroesAdapter: HeroesAdapter = HeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        binding.mRecyclerView.adapter = heroesAdapter
        viewModel.getHeroes(0,20)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                heroesAdapter.submitList(it)
            }
        }
    }

}