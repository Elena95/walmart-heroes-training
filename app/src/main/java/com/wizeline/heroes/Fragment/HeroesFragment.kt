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
    private lateinit var binding: FragmentHeroesBinding
    private val viewModel: HeroesViewModel = HeroesViewModel()
    private val heroesAdapter: HeroesAdapter = HeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        binding.mRecyclerView.adapter = heroesAdapter
        viewModel.getHeroes(0)

        binding.next.setOnClickListener {
            binding.prev.visibility = View.VISIBLE
            viewModel.nextPage()
        }
        binding.prev.setOnClickListener {
            binding.next.visibility = View.VISIBLE
            viewModel.prevPage()
            if (viewModel.currentPage > 0) {
                binding.next.text = "Next"
            } else {
                it.visibility = View.GONE
            }
        }
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isNotEmpty()) {
                    heroesAdapter.submitList(it)

                } else {
                    binding.next.visibility = View.GONE
                }
            }
        }

    }

}


