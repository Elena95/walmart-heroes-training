package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.wizeline.heroes.Adapter.HeroesAdapter
import com.wizeline.heroes.ViewModel.HeroesViewModel
import com.wizeline.heroes.ViewModel.MainViewModel
import com.wizeline.heroes.ViewModel.MyFragments
import com.wizeline.heroes.databinding.FragmentHeroesBinding

//activityViewModels es el viewModel del padre del fragment

class HeroesFragment : Fragment() {
    private lateinit var binding: FragmentHeroesBinding
    private val viewModel: HeroesViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private lateinit var heroesAdapter: HeroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHeroesBinding.inflate(inflater, container, false)

        heroesAdapter = HeroesAdapter(HeroesAdapter.OnClickListener {
            activityViewModel.ShowFragmentHeroes(MyFragments.DetailScreenFragment(it))
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        })

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


