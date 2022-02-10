package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.heroes.Adapter.HeroesAdapter
import com.wizeline.heroes.Result
import com.wizeline.heroes.ViewModel.SearchViewModel
import com.wizeline.heroes.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment() : Fragment() {
    private lateinit var binding: SearchFragmentBinding
    private lateinit var heroesAdapter: HeroesAdapter
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var layoutManager: LinearLayoutManager
    private var name = ""
    private val listResult = mutableListOf<Result>()
    private var goBack = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        layoutManager = LinearLayoutManager(context)
        binding.mRecyclerView.layoutManager = layoutManager
        navController = findNavController()
        search()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        heroesAdapter = HeroesAdapter(HeroesAdapter.OnClickListener {
            goBack = 1
            navController.navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailScreenFragment(
                    it,
                    it.name
                )
            )
        })
        binding.mRecyclerView.adapter = heroesAdapter
        binding.mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItemVisible(layoutManager)) {
                    viewModel.nextPage(name)
                }
            }
        })

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startPagin()
        heroesAdapter.submitList(listResult.toList())
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                listResult.clear()
                viewModel.offset = 0
                viewModel.searchHeroes(query, viewModel.offset)
                name = query
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    private fun isLastItemVisible(layoutManager: LinearLayoutManager) =
        layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1

    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                if (goBack == 0) {
                    if (it.isNotEmpty()) {
                        listResult.addAll(it)
                        heroesAdapter.submitList(listResult.toList())
                    }
                }
            }
        }
    }

}