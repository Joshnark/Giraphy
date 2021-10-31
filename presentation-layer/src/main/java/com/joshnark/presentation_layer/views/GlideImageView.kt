package com.joshnark.presentation_layer.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.joshnark.presentation_layer.R


class GlideImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int): AppCompatImageView(
    context,
    attrs,
    defStyleAttr
){

    var res: Int = -1
        set(value){
            field = value
            Glide.with(this).load(value).into(this)
        }

    init{
        val attributesArray = context.obtainStyledAttributes(attrs, R.styleable.GlideImageView)
        val attributeResource = attributesArray.getResourceId(R.styleable.GlideImageView_res, -1)
        loadImage(attributeResource)
        attributesArray.recycle()
    }

    constructor(context: Context, attrs: AttributeSet): this(context, attrs, 0)
    constructor(context: Context): this(context, null, 0)

    fun loadImage(resource: Int) {
        Glide
            .with(context)
            .load(resource)
            .into(this)
    }

    fun loadImage(url: String, onLoadFinished: (() -> Unit)? = null) {
        Glide
            .with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .addListener(
                object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        onLoadFinished?.invoke()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        onLoadFinished?.invoke()
                        return false
                    }
                }
            )
            .error(R.drawable.ic_triangle_exclamation_solid)
            .into(this)
    }

}