package com.wizeline.heroes.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.wizeline.heroes.Adapter.HeroesAdapter
import com.wizeline.heroes.ViewModel.SearchViewModel
import com.wizeline.heroes.databinding.SearchFragmentBinding

class SearchFragment() : Fragment() {
    private lateinit var binding: SearchFragmentBinding
    private lateinit var heroesAdapter: HeroesAdapter
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        heroesAdapter = HeroesAdapter(HeroesAdapter.OnClickListener {
            navController.navigate(
                HeroesFragmentDirections.actionHeroesFragmentToDetailScreenFragment(
                    it,
                    it.name
                )
            )
        })
        binding.mRecyclerView.adapter = heroesAdapter
        search()
        observeViewModel()
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchHeroes(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isNotEmpty()) {
                    heroesAdapter.submitList(it)
                }
            }
        }
    }

}