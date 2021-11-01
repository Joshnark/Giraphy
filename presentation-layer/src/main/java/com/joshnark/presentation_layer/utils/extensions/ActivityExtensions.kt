package com.joshnark.presentation_layer.utils.extensions

import android.os.Parcelable
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

inline fun <reified T: Parcelable> AppCompatActivity.parcelableExtra(key: String, default: T? = null) = lazy {
    val value = intent?.getParcelableExtra<T>(key)
    if (value is T) value else default
}
