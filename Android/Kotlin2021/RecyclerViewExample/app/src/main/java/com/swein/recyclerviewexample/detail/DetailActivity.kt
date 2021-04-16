package com.swein.recyclerviewexample.detail

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.swein.blurmaskloginexample.framework.util.thread.ThreadUtil
import com.swein.recyclerviewexample.R

class DetailActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    private var content = ""
    private var imageUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        checkBundle()
        findView()
        updateView()
    }

    private fun checkBundle() {
        intent.getBundleExtra("bundle").also {
            it?.let {
                content = it.getString("content", "")
                imageUrl = it.getString("imageUrl", "")
            }
        }
    }

    private fun findView() {
        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
    }

    private fun updateView() {

        textView.text = content

        imageView.post {

            // this part can only run on thread
            // we need a thread tool
            ThreadUtil.startThread {

                // if you don't know how to make a thread tool
                // you can watch the video first, I'll put this video on screen.
                val futureTarget: FutureTarget<Bitmap> = Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .submit()

                val bitmap = futureTarget.get()

                // back to main thread (UI thread)
                ThreadUtil.startUIThread(0) {
                    imageView.setImageBitmap(bitmap)
                }
            }
        }

    }
}