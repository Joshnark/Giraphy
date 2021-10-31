package com.joshnark.presentation_layer.utils.extensions

import android.animation.Animator
import android.animation.ValueAnimator
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.joshnark.presentation_layer.utils.listeners.CustomAnimatorListener
import com.joshnark.presentation_layer.utils.listeners.SafeClickListener

internal const val ALPHA_INVISIBLE = 0f
internal const val ALPHA_VISIBLE = 1f

internal const val ANIMATION_SPEED_NORMAL = 500L

internal fun View.show() {
    this.visibility = View.VISIBLE
}

internal fun View.hide() {
    this.visibility = View.GONE
}

internal fun View.show(show: Boolean) {
    if (show) this.show() else this.hide()
}

internal fun View.fadeOut(duration: Long = ANIMATION_SPEED_NORMAL, onEnd: (() -> Unit)? = null){
    if(this.isVisible) {
        this.animate()
            .alpha(ALPHA_INVISIBLE)
            .setDuration(duration)
            .setListener(object : CustomAnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                    this@fadeOut.hide()
                    this@fadeOut.alpha = alpha
                    onEnd?.invoke()
                }
            })
            .start()
    }
}

internal fun View.fadeIn(duration: Long = ANIMATION_SPEED_NORMAL, onEnd: (() -> Unit)? = null){
    if(this.isVisible.not()) {
        this.animate()
            .alpha(ALPHA_VISIBLE)
            .setDuration(duration)
            .setListener(object : CustomAnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    this@fadeIn.alpha = alpha
                    this@fadeIn.show()
                    onEnd?.invoke()
                }
            }).start()
    }
}

internal fun View.fade(show: Boolean, duration: Long = ANIMATION_SPEED_NORMAL, onEnd: (() -> Unit)? = null){
    if (show) fadeIn(duration, onEnd) else fadeOut(duration, onEnd)
}

internal fun ProgressBar.addCustomAnimation() {
    ValueAnimator.ofInt(0, 100).apply {
        interpolator = LinearInterpolator()
        duration = 1000
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener {
            (it.animatedValue as? Int)?.let {
                this@addCustomAnimation.progress = it
            }
        }
        start()
    }
}

internal fun View.setOnSafeClickListener(clickListener: (View) -> Unit) {
    setOnClickListener(SafeClickListener(clickListener))
}

internal fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

internal fun View.openKeyboardAndFocus(){
    openKeyboard()
    this.requestFocus()
}

internal fun View.openKeyboard(){
    (ContextCompat.getSystemService(this.context, InputMethodManager::class.java) as InputMethodManager).toggleSoftInput(
        InputMethodManager.SHOW_IMPLICIT,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

internal fun View.closeKeyboard() {
    (ContextCompat.getSystemService(
        this.context,
        InputMethodManager::class.java
    ) as InputMethodManager)
        .hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
}