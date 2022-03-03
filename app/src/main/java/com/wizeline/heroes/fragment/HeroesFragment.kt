package com.wizeline.heroes.fragment

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
import com.wizeline.heroes.adapter.HeroesAdapter
import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.viewModel.HeroesViewModel
import com.wizeline.heroes.databinding.FragmentHeroesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesFragment : Fragment() {
    private lateinit var binding: FragmentHeroesBinding
    private val viewModel: HeroesViewModel by viewModels()
    private lateinit var heroesAdapter: HeroesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var navController: NavController
   // private var listResult = mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
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
            viewModel.getListHeroes()
    }

    private fun isLastItemVisible(layoutManager: LinearLayoutManager) =
        layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1

    private fun observeViewModel() {
        viewModel.resultData.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isNotEmpty()) {
                    heroesAdapter.submitList(it.toMutableList())
                }
            }
        }
    }
}




