package com.swein.customizenavigationbar

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.swein.customizenavigationbar.custom_navigation_bar.CustomNavigationBar

/**
 * Hello, let's build a customize navigation bar, ok let's go
 * 1. setting project
 * 2. remove navigation bar and run app, ok
 * 3. create a navigation bar class
 * 4. create a xml file for navigation bar class
 * 5. let's finish navigation bar xml and class
 * 6. let's add navigation bar to main screen
 * 7. ok, let's try it
 * 8. not beautiful, let's keep going
 * 9. good, let's try again
 * 10. thanks
 */
class MainActivity : FragmentActivity() {

    private lateinit var frameLayoutNavigationBarContainer: FrameLayout
    private lateinit var customNavigationBar: CustomNavigationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        findView()
        initNavigationBar()
    }

    private fun findView() {
        frameLayoutNavigationBarContainer = findViewById(R.id.frameLayoutNavigationBarContainer)
    }

    private fun initNavigationBar() {
        customNavigationBar = CustomNavigationBar(this)

        customNavigationBar.setTitle("test").setLeftImage(R.mipmap.ti_back).setRightImage(R.mipmap.ti_menu)
                .setDelegate(object : CustomNavigationBar.CustomNavigationBarDelegate {
                    override fun onLeftClick() {
                        Log.d("???", "I click left menu in navigation bar")
                    }

                    override fun onRightClick() {
                        Log.d("???", "I click right menu in navigation bar")
                    }

                })

        frameLayoutNavigationBarContainer.addView(customNavigationBar.view)
    }
}