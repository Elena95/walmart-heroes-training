package com.wizeline.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.wizeline.heroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   // private lateinit var binding: ActivityMainBinding
  //  private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navControllerFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navControllerFragment.navController

        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)


        navControllerFragment.navController.graph = graph*/
    }

}
