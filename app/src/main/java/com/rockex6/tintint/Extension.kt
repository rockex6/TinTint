package com.rockex6.tintint

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat


fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .format(DecodeFormat.PREFER_RGB_565)
//            .placeholder(R.drawable.loading_photo)
        .into(this)
}

