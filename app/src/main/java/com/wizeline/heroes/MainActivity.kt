package com.wizeline.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.wizeline.heroes.Fragment.DetailScreenFragment
import com.wizeline.heroes.Fragment.HeroesFragment
import com.wizeline.heroes.ViewModel.MainViewModel
import com.wizeline.heroes.ViewModel.MyFragments
import com.wizeline.heroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    //  private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observerFragment()

        /* binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)


         val navControllerFragment =
             supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navControllerFragment.navController

         val inflater = navController.navInflater
         val graph = inflater.inflate(R.navigation.nav_graph)


         navControllerFragment.navController.graph = graph*/
    }

    private fun observerFragment() {
        viewModel.destination.observe(this) {
            when (it) {
                is MyFragments.HeroFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentHeroes, HeroesFragment())

                    }
                }
                is MyFragments.DetailScreenFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentHeroes, DetailScreenFragment(it.dataHero))
                    }
                }
            }
        }

    }

}
