package com.wizeline.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

   /* private val privateKey = "244be7c2496cbf6d331145cc489b4892457cc2c0"
    private val apikey = "6ffcf49b680b7250a6983acd33731f55"
    private val ts = System.currentTimeMillis().toString()*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //requestCharacters()
       // println("TS: $ts")
    }

   /* private fun requestCharacters() {
        NetworkClient().getCharacters(
            onSuccess = {
                println("success: $it")
            },
            onError= {
                println(it)

            }
        )

    }*/
}
