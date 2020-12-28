package com.bmnmrls.astro.splash.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.bmnmrls.astro.databinding.ActivitySplashBinding
import com.bmnmrls.astro.home.views.activity.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.splashMotionLayout.addTransitionListener(
            object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) = Unit

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) = Unit
                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) =
                    Unit

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    HomeActivity.launch(this@SplashActivity)
                    finish()
                }
            }
        )
    }

}