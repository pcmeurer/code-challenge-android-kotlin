package com.arctouch.codechallenge.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("loadPosterImage")
    fun loadPosterImage(view: ImageView, url: String?) {
        Glide.with(view)
                .load(url?.let { MovieImageUrlBuilder().buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("loadBackdropImage")
    fun loadBackdropImage(view: ImageView, url: String?) {
        Glide.with(view)
                .load(url?.let { MovieImageUrlBuilder().buildBackdropUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(view)
    }
}