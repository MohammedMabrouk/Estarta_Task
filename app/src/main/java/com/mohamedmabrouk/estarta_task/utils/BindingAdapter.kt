package com.mohamedmabrouk.estarta_task.utils

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mohamedmabrouk.estarta_task.R
import com.squareup.picasso.Picasso


@BindingAdapter("loadingPb")
fun bindLoadingPb(progressBar: ProgressBar, isLoading: Boolean) {
    if (isLoading)
        progressBar.visibility = View.VISIBLE
    else
        progressBar.visibility = View.GONE
}

@BindingAdapter("errorText")
fun bindErrorText(textView: TextView, error: String?) {
    if (error.isNullOrEmpty()) {
        textView.visibility = View.INVISIBLE
    } else {
        textView.text = error
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("bindRetryBtn")
fun bindRetryBtn(button: Button, error: String?) {
    if (error.isNullOrEmpty()) {
        button.visibility = View.INVISIBLE
    } else {
        button.visibility = View.VISIBLE
    }
}

@BindingAdapter("pictureUrl")
fun bindUriToImage(imageView: ImageView, imageUrls: List<String?>?) {
    if (!imageUrls.isNullOrEmpty() && !imageUrls[0].isNullOrEmpty())
        Picasso.get()
            .load(imageUrls[0])
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(imageView)
}
