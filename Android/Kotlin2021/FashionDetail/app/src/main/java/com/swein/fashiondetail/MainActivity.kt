package com.swein.fashiondetail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily

/**
 * Hello, let's build a fashion look detail page
 * 1. project setting
 * 2. finish xml
 * 3. update view
 *
 * 4. ok, let's run app, not good enough
 * let's run again, good, thanks
 *
 * youtube video: https://youtu.be/kVEtKW9W7FY
 */
class MainActivity : AppCompatActivity() {

    private lateinit var shapeableImageView: ShapeableImageView
    private lateinit var textViewCategory: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var textViewS: TextView
    private lateinit var textViewM: TextView
    private lateinit var textViewL: TextView
    private lateinit var textViewXL: TextView
    private lateinit var textViewXXL: TextView
    private lateinit var buttonContinue: Button
    private lateinit var imageButtonMenu: ImageButton
    private lateinit var imageButtonLike: ImageButton
    private lateinit var imageButtonCart: ImageButton

    private var isCart = false
    private var isLike = false

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

        shapeableImageView = findViewById(R.id.shapeableImageView)
        textViewCategory = findViewById(R.id.textViewCategory)
        textViewName = findViewById(R.id.textViewName)
        textViewPrice = findViewById(R.id.textViewPrice)
        textViewDescription = findViewById(R.id.textViewDescription)
        textViewS = findViewById(R.id.textViewS)
        textViewM = findViewById(R.id.textViewM)
        textViewL = findViewById(R.id.textViewL)
        textViewXL = findViewById(R.id.textViewXL)
        textViewXXL = findViewById(R.id.textViewXXL)
        buttonContinue = findViewById(R.id.buttonContinue)
        imageButtonMenu = findViewById(R.id.imageButtonMenu)
        imageButtonLike = findViewById(R.id.imageButtonLike)
        imageButtonCart = findViewById(R.id.imageButtonCart)

        val radius = dipToPx(36f)
        shapeableImageView.shapeAppearanceModel = shapeableImageView.shapeAppearanceModel.toBuilder()
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius.toFloat())
            .build()
    }

    private fun dipToPx(dipValue: Float): Int {
        val scale = this.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    private fun setListener() {
        textViewS.setOnClickListener {
            resetSizeUI()
            it.setBackgroundResource(R.drawable.size_selection_bg)
        }
        textViewM.setOnClickListener {
            resetSizeUI()
            it.setBackgroundResource(R.drawable.size_selection_bg)
        }
        textViewL.setOnClickListener {
            resetSizeUI()
            it.setBackgroundResource(R.drawable.size_selection_bg)
        }
        textViewXL.setOnClickListener {
            resetSizeUI()
            it.setBackgroundResource(R.drawable.size_selection_bg)
        }
        textViewXXL.setOnClickListener {
            resetSizeUI()
            it.setBackgroundResource(R.drawable.size_selection_bg)
        }

        buttonContinue.setOnClickListener {
            Toast.makeText(it.context, "click continue", Toast.LENGTH_SHORT).show()
        }
        imageButtonMenu.setOnClickListener {
            Toast.makeText(it.context, "click menu", Toast.LENGTH_SHORT).show()
        }
        imageButtonLike.setOnClickListener {
            isLike = !isLike

            toggleLike()
        }
        imageButtonCart.setOnClickListener {
            isCart = !isCart

            toggleCart()
        }
    }

    private fun resetSizeUI() {
        textViewS.setBackgroundResource(R.drawable.size_un_selection_bg)
        textViewM.setBackgroundResource(R.drawable.size_un_selection_bg)
        textViewL.setBackgroundResource(R.drawable.size_un_selection_bg)
        textViewXL.setBackgroundResource(R.drawable.size_un_selection_bg)
        textViewXXL.setBackgroundResource(R.drawable.size_un_selection_bg)
    }

    private fun toggleLike() {
        if (isLike) {
            imageButtonLike.setImageResource(R.mipmap.like_on)
        }
        else {
            imageButtonLike.setImageResource(R.mipmap.like_off)
        }
    }

    private fun toggleCart() {
        if (isCart) {
            imageButtonCart.setImageResource(R.mipmap.cart_on)
        }
        else {
            imageButtonCart.setImageResource(R.mipmap.cart_off)
        }
    }

    private fun updateView() {

        shapeableImageView = findViewById(R.id.shapeableImageView)
        textViewCategory = findViewById(R.id.textViewCategory)
        textViewName = findViewById(R.id.textViewName)
        textViewPrice = findViewById(R.id.textViewPrice)
        textViewDescription = findViewById(R.id.textViewDescription)

        Glide.with(this).asBitmap().load(R.mipmap.example).transition(BitmapTransitionOptions.withCrossFade())
            .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(shapeableImageView)

        textViewCategory.text = "sweater"
        textViewName.text = "Summer look short sweater"
        textViewPrice.text = "$260"
        textViewDescription.text = "description about summer look short sweater, description about summer look short sweater, description about summer look short sweater, Please subscribe to my channel, Coding with cat"


    }
}