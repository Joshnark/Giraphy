package com.joshnark.presentation_layer.utils.listeners

import android.animation.Animator

interface CustomAnimatorListener : Animator.AnimatorListener{
    override fun onAnimationEnd(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onAnimationStart(animation: Animator?) {}
}