package com.joshnark.presentation_layer.utils.listeners

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

class SafeClickListener(private val clickListener: (View) -> Unit): View.OnClickListener{

    private var canClick = AtomicBoolean(true)

    override fun onClick(view: View?) {
        if(canClick.getAndSet(false)){
            view?.run{

                postDelayed({
                    canClick.set(true)
                },
                    CLICK_DELAY_INTERVAL
                )

                clickListener.invoke(view)
            }
        }
    }

    companion object{
        private const val CLICK_DELAY_INTERVAL = 2000L
    }

}