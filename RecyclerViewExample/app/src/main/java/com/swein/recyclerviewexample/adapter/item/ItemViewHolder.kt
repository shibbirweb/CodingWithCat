package com.swein.recyclerviewexample.adapter.item

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.swein.blurmaskloginexample.framework.util.thread.ThreadUtil
import com.swein.recyclerviewexample.R
import java.lang.ref.WeakReference

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    interface ItemViewHolderDelegate {
        fun onItemViewClick(itemBean: ItemBean)
    }

    // use weak reference to keep low memory use
    private var view: WeakReference<View> = WeakReference(itemView)

    private lateinit var imageView: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewContent: TextView

    var delegate: ItemViewHolderDelegate? = null
    lateinit var itemBean: ItemBean

    init {

        findView()
        setListener()
    }

    private fun findView() {
        view.get()?.let {
            imageView = it.findViewById(R.id.imageView)
            textViewTitle = it.findViewById(R.id.textViewTitle)
            textViewContent = it.findViewById(R.id.textViewContent)
        }

    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            delegate?.onItemViewClick(itemBean)
        }
    }

    fun updateView() {
        textViewTitle.text = itemBean.title
        textViewContent.text = itemBean.content

        imageView.post {

            view.get()?.let {


                // this part can only run on thread
                // we need a thread tool
                ThreadUtil.startThread {

                    // if you don't know how to make a thread tool
                    // you can watch the video first, I'll put this video on screen.
                    val futureTarget: FutureTarget<Bitmap> = Glide.with(it.context)
                        .asBitmap()
                        .load(itemBean.imageUrl)
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
}