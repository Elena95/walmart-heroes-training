package com.wizeline.heroes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wizeline.heroes.ViewModel.MainViewModel
import com.wizeline.heroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigation.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController)

    }

    /*  private fun observerFragment() {
        viewModel.destination.observe(this) {
            when (it) {
                is MyFragments.HeroFragment -> {
                    supportFragmentManager.commit {
                        //navController.navigate(R.id.heroesFragment)

                        replace(R.id.heroesFragment, HeroesFragment())

                    }
                }
                is MyFragments.DetailScreenFragment -> {
                    supportFragmentManager.commit {
                        //navController.navigate(R.id.detailScreenFragment)

                        replace(R.id.detailScreenFragment, DetailScreenFragment(it.dataHero))
                    }
                }
                *//* is MyFragments.SearchFragment -> {
                     supportFragmentManager.commit {
                         replace(R.id.fragmentHeroes, SearchFragment())
                     }
                 }*//*

            }
        }

    }*/
}
