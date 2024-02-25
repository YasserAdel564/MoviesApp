package com.example.movies.presentation.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.R

fun loadImageUrl(image: ImageView, url: String?) {
    url?.let {
        Glide.with(image.context).load(url).placeholder(R.drawable.movie_placeholder).into(image)
    }
}

fun attachImageBaseUrl(context: Context, imagePath: String): String {
    return context.getString(R.string.image_url) + imagePath
}