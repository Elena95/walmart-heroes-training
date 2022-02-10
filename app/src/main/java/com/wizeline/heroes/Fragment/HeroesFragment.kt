package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.heroes.Adapter.HeroesAdapter
import com.wizeline.heroes.Result
import com.wizeline.heroes.ViewModel.HeroesViewModel
import com.wizeline.heroes.databinding.FragmentHeroesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesFragment : Fragment() {
    private lateinit var binding: FragmentHeroesBinding
    private val viewModel: HeroesViewModel by viewModels()
    private lateinit var heroesAdapter: HeroesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var navController: NavController
    private var listResult = mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        layoutManager = LinearLayoutManager(context)
        binding.mRecyclerView.layoutManager = layoutManager
        navController = findNavController()
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
        binding.mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItemVisible(layoutManager)) {
                    viewModel.nextPage()
                }
            }
        })
        observeViewModel()
        if(listResult.size==0) {
            viewModel.getHeroes(viewModel.offset)
        }else{
            heroesAdapter.submitList(listResult.toMutableList())
        }
    }

    private fun isLastItemVisible(layoutManager: LinearLayoutManager) =
        layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1

    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                var idLastHeroList=0
                if(listResult.size>0) {
                    idLastHeroList = listResult[listResult.size - 1].id
                }
                var idHeroService=0
                if(it.size>0){
                    idHeroService = it[it.size - 1].id
                }
                if(listResult.size==0||
                    (idLastHeroList!=idHeroService)) {
                    if (it.isNotEmpty()) {
                        listResult.addAll(it)
                        heroesAdapter.submitList(listResult.toMutableList())
                    }
                }

            }
        }
    }
}




