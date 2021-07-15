package com.shohiebsense.myapplication.ui

import android.view.View
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.setDrawableLeft(@ColorRes res: Int) {
	if (compoundDrawables[0] == null) return
	compoundDrawables[0].setTint(ContextCompat.getColor(context, res))
}


fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, observer: (T) -> Unit) =
	liveData?.observe(this, Observer(observer))

fun View.show(){
	this.visibility = View.VISIBLE
}

fun View.hide(){
	this.visibility = View.INVISIBLE
}

fun Button.enable(){
	this.isEnabled = true
}

fun Button.disable(){
	this.isEnabled = false
}
