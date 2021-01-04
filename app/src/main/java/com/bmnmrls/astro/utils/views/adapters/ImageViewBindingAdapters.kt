package com.bmnmrls.astro.utils.views.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bmnmrls.astro.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.loadImageUrl(imageUrl: String) {
    val requestOptions: RequestOptions = RequestOptions()
        .transform(CenterCrop())
        .error(R.drawable.ic_error)
    Glide.with(context)
        .load(imageUrl)
        .apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}