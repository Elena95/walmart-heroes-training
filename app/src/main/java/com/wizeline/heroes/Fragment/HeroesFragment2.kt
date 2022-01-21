package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wizeline.heroes.HeroesAdapter
import com.wizeline.heroes.ViewModel.HeroesViewModel
import com.wizeline.heroes.databinding.FragmentHeroes2Binding

class HeroesFragment2 : Fragment() {

    lateinit var binding: FragmentHeroes2Binding
    private val viewModel: HeroesViewModel = HeroesViewModel()
    private val heroesAdapter: HeroesAdapter = HeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHeroes2Binding.inflate(inflater, container, false)
        binding.mRecyclerView.adapter = heroesAdapter
        viewModel.getHeroes(20,40)
        observeViewModel()
        return binding.root
    }

    /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     }*/
    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                heroesAdapter.submitList(it)
            }
        }
    }
}// Required empty public constructor