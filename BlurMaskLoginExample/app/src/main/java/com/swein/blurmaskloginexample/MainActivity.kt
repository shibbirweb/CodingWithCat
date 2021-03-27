package com.swein.blurmaskloginexample

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.swein.blurmaskloginexample.framework.util.bitmap.BitmapUtil
import com.swein.blurmaskloginexample.framework.util.edittext.EditTextUtil
import com.swein.blurmaskloginexample.framework.util.thread.ThreadUtil

/**
 * video about this project
 * https://youtu.be/OmQ2BxdZlEA
 *
 * 1. setting project
 * 2. make thread util
 * 3. UI
 * 4. Activity
 * 5. set listener
 * 6. check code and hide status bar
 *
 * thanks
 */
class MainActivity : FragmentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var imageViewBackground: ImageView
    private lateinit var imageViewBlur: ImageView
    private lateinit var editTextID: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var textViewSkip: TextView
    private lateinit var frameLayoutInputArea: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        findView()
        setListener()
        updateView()
    }

    private fun findView() {
        imageViewBackground = findViewById(R.id.imageViewBackground)
        imageViewBlur = findViewById(R.id.imageViewBlur)
        editTextID = findViewById(R.id.editTextID)
        editTextPassword = findViewById(R.id.editTextPassword)
        textViewSkip = findViewById(R.id.textViewSkip)
        frameLayoutInputArea = findViewById(R.id.frameLayoutInputArea)
    }

    private fun setListener() {

        textViewSkip.setOnClickListener {
            Log.d(TAG, "I'll skip login!!!")
        }

        // after input id, cursor will auto move to password edit text view
        // we need a tool of jump focus between two edit text view

        // let's try
        // ok
        EditTextUtil.jumpFocus(fromEditText = editTextID, targetEditText = editTextPassword)

        // action go login
        editTextPassword.setOnEditorActionListener { textView, actionId, keyEvent ->

            return@setOnEditorActionListener when(actionId) {

                EditorInfo.IME_ACTION_GO -> {

                    // we need check id here and password

                    val idString = editTextID.text.toString().trim()
                    if (idString == "") {

                        Toast.makeText(this, "input id please", Toast.LENGTH_SHORT).show()

                        return@setOnEditorActionListener true
                    }

                    val passwordString = editTextPassword.text.toString().trim()
                    if (passwordString == "") {

                        Toast.makeText(this, "input password please", Toast.LENGTH_SHORT).show()

                        return@setOnEditorActionListener true
                    }

                    Log.d(TAG, "login go go go !")

                    true
                }
                else -> false
            }

        }

    }

    private fun updateView() {

        imageViewBackground.post {

            // glide can only run on thread
            ThreadUtil.startThread {

                // any image link from internet
                val imageUrl = "https://cdn.wallpapersafari.com/15/87/kp4wAJ.jpg"

                val futureTarget: FutureTarget<Bitmap> = Glide.with(this)
                        .asBitmap()
                        .load(imageUrl)
                        .submit(imageViewBackground.width, imageViewBackground.height)

                val bitmap = futureTarget.get()

                // back to main thread here (UI thread)
                ThreadUtil.startUIThread(0) {
                    imageViewBackground.setImageBitmap(bitmap)

                    frameLayoutInputArea.post {

                        // draw background image into canvas
                        var cropBitmap = Bitmap.createBitmap(imageViewBackground.width, imageViewBackground.height, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(cropBitmap)
                        imageViewBackground.draw(canvas)

                        // crop background image
                        // size and position must be the parent of imageViewBlur
                        cropBitmap = Bitmap.createBitmap(cropBitmap, frameLayoutInputArea.x.toInt(), frameLayoutInputArea.y.toInt(), frameLayoutInputArea.width, frameLayoutInputArea.height)

                        // blur the bitmap
                        // set image
                        // ok now is good
                        imageViewBlur.setImageBitmap(BitmapUtil.blurBitmap(this, cropBitmap))

                    }

                }
            }

        }
    }


}