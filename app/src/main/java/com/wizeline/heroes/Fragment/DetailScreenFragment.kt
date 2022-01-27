package com.wizeline.heroes.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.wizeline.heroes.Adapter.ComicsAdapter
import com.wizeline.heroes.Adapter.SeriesAdapter
import com.wizeline.heroes.R
import com.wizeline.heroes.Result

import com.wizeline.heroes.ViewModel.DetailScreenViewModel
import com.wizeline.heroes.databinding.DetailScreenFragmentBinding

class DetailScreenFragment(private val dataHero: Result) : Fragment() {
    private lateinit var binding: DetailScreenFragmentBinding
    private val comicsAdapter = ComicsAdapter()
    private val seriesAdapter = SeriesAdapter()
    private val viewModel: DetailScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
        viewModel.getComics(dataHero)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar, menu)
        //return super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mrvComics.adapter = comicsAdapter
        binding.mrvSeries.adapter = seriesAdapter
        infoHero(dataHero)
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


}