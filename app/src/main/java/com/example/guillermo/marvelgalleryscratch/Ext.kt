package com.example.guillermo.marvelgalleryscratch

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
            .load(imageUrl)
            .into(this);

}

fun View.toast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}

fun String.toCamelCase(): String {
    return this.first().toUpperCase() + this.substring(1).toLowerCase()
}