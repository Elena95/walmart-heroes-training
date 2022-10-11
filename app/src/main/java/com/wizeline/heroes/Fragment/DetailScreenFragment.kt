package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.wizeline.heroes.*
import com.wizeline.heroes.Adapter.ComicsAdapter
import com.wizeline.heroes.Adapter.SeriesAdapter
import com.wizeline.heroes.Repository.RepositoryImpl

import com.wizeline.heroes.ViewModel.DetailScreenViewModel
import com.wizeline.heroes.ViewModel.HeroesViewModel
import com.wizeline.heroes.databinding.DetailScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment() : Fragment() {
    private lateinit var binding: DetailScreenFragmentBinding
    private val comicsAdapter = ComicsAdapter()
    private val seriesAdapter = SeriesAdapter()
    private val viewModel: DetailScreenViewModel by viewModels()
    private val viewModelHeroes: HeroesViewModel by viewModels()

    //private val viewModel: DetailScreenViewModel by viewModels()
    private val args: DetailScreenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
       // viewModel = ViewModelProvider(this,getViewModelFactory()).get(DetailScreenViewModel::class.java)

        viewModel.getComics(args.result)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mrvComics.adapter = comicsAdapter
        binding.mrvSeries.adapter = seriesAdapter
        infoHero(args.result)
        setUpObservers()

    }

    fun infoHero(hero: Result) = with(binding) {
        tvTitle.text = hero.name
        tvDescrip.text = hero.description
        Glide.with(binding.root.context)
            .load("${hero.thumbnail.path}.${hero.thumbnail.extension}")
            .into(imageHeroe)
        //.error(R.drawable.ic_launcher_foreground)
    }

    private fun setUpObservers() {
        viewModel.comicsData.observe(viewLifecycleOwner) {

            it?.let {
                comicsAdapter.submitList(it)
            }
        }

        viewModel.seriesData.observe(viewLifecycleOwner) {
            it?.let {
                seriesAdapter.submitList(it)
            }
        }
    }
/*
    private fun getViewModelFactory(): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailScreenViewModel(GetComicsUsesCaseImp(RepositoryImpl()),GetSeriesUsesCaseImp(RepositoryImpl())) as T
            }

        }*/


}