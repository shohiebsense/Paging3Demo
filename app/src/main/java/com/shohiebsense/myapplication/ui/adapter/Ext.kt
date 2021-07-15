package com.shohiebsense.myapplication.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.shohiebsense.myapplication.R
import com.shohiebsense.myapplication.model.Status
import com.shohiebsense.myapplication.ui.setDrawableLeft

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
	if (url.isNullOrEmpty()) return
	Glide.with(this).load(url).into(this)
}

@BindingAdapter("status")
fun MaterialTextView.status(status: Status) {
	text = status.toString()
	when (status) {
		Status.ALIVE -> setDrawableLeft(R.color.green_a700)
		else -> setDrawableLeft(0)
	}
}