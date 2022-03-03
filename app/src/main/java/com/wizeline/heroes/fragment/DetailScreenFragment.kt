package com.wizeline.heroes.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.wizeline.heroes.adapter.ComicsAdapter
import com.wizeline.heroes.adapter.SeriesAdapter
import com.wizeline.heroes.data.entities.Result
import com.wizeline.heroes.viewModel.DetailScreenViewModel
import com.wizeline.heroes.databinding.DetailScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment() : Fragment() {
    private lateinit var binding: DetailScreenFragmentBinding
    private val comicsAdapter = ComicsAdapter()
    private val seriesAdapter = SeriesAdapter()
    private val viewModel: DetailScreenViewModel by viewModels()
    private val args: DetailScreenFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
        viewModel.getComics(args.result)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mrvComics.adapter = comicsAdapter
        binding.mrvSeries.adapter = seriesAdapter
        infoHero(args.result)
        setUpObservers()
    }

    private fun infoHero(hero: Result) = with(binding) {
        tvTitle.text = hero.name
        tvDescrip.text = hero.description
        Glide.with(binding.root.context)
            .load("${hero.thumbnail.path}.${hero.thumbnail.extension}")
            .into(imageHeroe)
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
}