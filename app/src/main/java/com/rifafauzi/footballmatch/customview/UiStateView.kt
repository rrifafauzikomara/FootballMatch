package com.rifafauzi.footballmatch.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.rifafauzi.footballmatch.R

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */

class UiStateView : ConstraintLayout {

    private lateinit var tvResult: TextView
    private lateinit var ivResult: ImageView
    private lateinit var view: View
    private lateinit var progressBar: ProgressBar

    private var text: String? = null
    private var imageDrawable: Drawable? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttributes(attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttributes(attrs)
        init()
    }

    fun showLoading() {
        showProgressBar()
        setText(context.getString(R.string.please_wait))
        resetState()
    }

    fun hideLoading() {
        hideProgressBar()
    }

    fun showNoData(@StringRes onNoDataMessageResId: Int) {
        setText(context.getString(onNoDataMessageResId))
        setImageResource(ContextCompat.getDrawable(context, R.drawable.ic_no_data))
        hideProgressBar()
    }

    fun showError(@StringRes errorMessageResId: Int) {
        setText(context.getString(errorMessageResId))
        setImageResource(ContextCompat.getDrawable(context, R.drawable.ic_error))
        hideProgressBar()
    }

    fun showHasData() {
        resetState()
        hideProgressBar()
    }

    private fun init() {
        view = inflate(context, R.layout.custom_view_result, this)
        tvResult = view.findViewById(R.id.tvResult)
        ivResult = view.findViewById(R.id.ivResult)
        progressBar = view.findViewById(R.id.progressBar)

        setText(text)
        setImageResource(imageDrawable)
        showProgressBar()
    }

    private fun getAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UiStateView)

        text = typedArray.getString(R.styleable.UiStateView_text)
        imageDrawable = typedArray.getDrawable(R.styleable.UiStateView_imageSrc)

        typedArray.recycle()
    }

    private fun setText(text: String?) {
        tvResult.visibility = View.VISIBLE
        tvResult.text = text
    }

    private fun setImageResource(imageDrawable: Drawable?) {
        ivResult.visibility = View.VISIBLE
        ivResult.setImageDrawable(imageDrawable)
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun resetState() {
        tvResult.visibility = View.GONE
        ivResult.visibility = View.GONE
    }
}