package com.swein.customizenavigationbar.custom_navigation_bar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.swein.customizenavigationbar.R

/**
 * I'll build a custom navigation bar with a title, and two button in it
 */
class CustomNavigationBar(context: Context) {

    interface CustomNavigationBarDelegate {
        fun onLeftClick()
        fun onRightClick()
    }

    var view: View = LayoutInflater.from(context).inflate(R.layout.view_holder_custom_navigation_bar, null, false)

    private lateinit var imageViewLeft: ImageView
    private lateinit var imageViewRight: ImageView
    private lateinit var textViewTitle: TextView

    private var delegate: CustomNavigationBarDelegate? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
        imageViewLeft = view.findViewById(R.id.imageViewLeft)
        imageViewRight = view.findViewById(R.id.imageViewRight)
        textViewTitle = view.findViewById(R.id.textViewTitle)
    }

    private fun setListener() {
        imageViewLeft.setOnClickListener {
            delegate?.onLeftClick()
        }

        imageViewRight.setOnClickListener {
            delegate?.onRightClick()
        }
    }

    fun setDelegate(delegate: CustomNavigationBarDelegate): CustomNavigationBar {
        this.delegate = delegate
        return this
    }

    fun setLeftImage(resourceId: Int): CustomNavigationBar {
        imageViewLeft.setImageResource(resourceId)
        return this
    }

    fun setRightImage(resourceId: Int): CustomNavigationBar {
        imageViewRight.setImageResource(resourceId)
        return this
    }

    fun setTitle(title: String): CustomNavigationBar {
        textViewTitle.text = title
        return this
    }

    fun showLeft(shouldShow: Boolean): CustomNavigationBar {
        imageViewLeft.visibility = if(shouldShow) {
            View.VISIBLE
        }
        else {
            View.GONE
        }

        return this
    }

    fun showRight(shouldShow: Boolean): CustomNavigationBar {
        imageViewRight.visibility = if(shouldShow) {
            View.VISIBLE
        }
        else {
            View.GONE
        }

        return this
    }

}