package com.joshnark.presentation_layer.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joshnark.domain_layer.models.Gif
import com.joshnark.presentation_layer.databinding.ActivityGifDetailBinding
import com.joshnark.presentation_layer.utils.extensions.parcelableExtra
import com.joshnark.presentation_layer.utils.extensions.viewBinding

class GifDetailActivity : AppCompatActivity() {

    private val gif by parcelableExtra<Gif>(GIF_EXTRA)
    private val binding by viewBinding(ActivityGifDetailBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkDataIntegrity()
    }

    private fun checkDataIntegrity() {
        if (gif == null) {
            Toast.makeText(this, "An error ocurred", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        private const val GIF_EXTRA = "gif_extra"
    }
}