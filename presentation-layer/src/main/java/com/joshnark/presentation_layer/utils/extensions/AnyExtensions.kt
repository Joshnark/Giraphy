package com.joshnark.presentation_layer.utils.extensions

/** extension function to convert value, if the conversion doesn't succeed, the callback wont run */
inline fun <reified T> Any.asType(block: (T) -> Unit) {
    (this as? T)?.let(block)
}