package com.wizeline.heroes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private val splashTimeout: Long = 2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* val configSettings:FirebaseRemoteConfigSettings= remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }
        val fireBaseConfig:FirebaseRemoteConfig =Firebase.remoteConfig
        fireBaseConfig.setConfigSettingsAsync(configSettings)*/

        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val isAppBlocked = Firebase.remoteConfig.getBoolean("isAppBlocked")
                if (isAppBlocked) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        Toast.makeText(this, "The application can't be used at the moment", Toast.LENGTH_LONG).show()
                        moveTaskToBack(true);
                        exitProcess(-1)
                    }, splashTimeout)

                } else {
                    setContentView(R.layout.splash_screen)
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }, splashTimeout)
                }
            }
        }
    }
}