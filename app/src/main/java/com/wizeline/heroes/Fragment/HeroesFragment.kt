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
    var currentPage = 0;
    var offset = 0;
    var maxPage = 0;

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
        maxPage=100/5
            if(currentPage < maxPage-1){
                offset +=5
                viewModel.getHeroes(offset)
                currentPage++
            }else{
                it.visibility = View.GONE
            }
        }

        binding.prev.setOnClickListener {
            binding.next.visibility = View.VISIBLE
            if (currentPage > 0) {
                currentPage--
                viewModel.getHeroes(offset-5)
                offset -=5
                binding.next.text = "Next"

            } else {
                viewModel.getHeroes(offset)
                it.visibility = View.GONE
            }

        }
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


