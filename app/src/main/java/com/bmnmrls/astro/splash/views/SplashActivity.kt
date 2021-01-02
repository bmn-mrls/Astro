package com.bmnmrls.astro.splash.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bmnmrls.astro.home.views.activity.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchHomeScreen()
    }

    private fun launchHomeScreen() {
        HomeActivity.launch(this@SplashActivity)
        finish()
    }

}