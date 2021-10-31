package com.joshnark.presentation_layer.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joshnark.presentation_layer.R
import com.joshnark.presentation_layer.databinding.ActivitySplashBinding
import com.joshnark.presentation_layer.utils.extensions.fadeIn
import com.joshnark.presentation_layer.utils.extensions.fadeOut
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runSplashAnimation()
    }

    private fun runSplashAnimation() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.imageViewLogo.fadeIn(FADE_DURATION)
            binding.textViewSampleText1.fadeIn(FADE_DURATION)

            delay(FADE_DURATION * 3)

            binding.imageViewLogo.fadeOut(FADE_DURATION)
            binding.textViewSampleText1.fadeOut(FADE_DURATION)

            delay(FADE_DURATION)

            openMainActivity()
        }
    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        private const val FADE_DURATION = 1000L
    }

}