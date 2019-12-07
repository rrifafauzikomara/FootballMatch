package com.rifafauzi.footballmatch.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifafauzi.footballmatch.R

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl.isNullOrEmpty()) {
        Glide.with(imgView.context)
            .load(R.drawable.ic_no_data)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_error))
            .into(imgView)
    } else {
        imgUrl.let {
            val imgUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_place_holder)
                        .error(R.drawable.ic_error))
                .into(imgView)
        }
    }
}