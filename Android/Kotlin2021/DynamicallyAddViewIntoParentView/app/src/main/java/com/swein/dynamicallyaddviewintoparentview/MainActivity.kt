package com.swein.dynamicallyaddviewintoparentview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout

/**
 * How to dynamically add a view into parent view?? let's do it
 *
 * 1. create a view
 * 2. create a sub view container and add sub view into it
 * 3. let's run app, good
 * 4. how to move sub view into other parent view ? let's do it
 * 5. let's run app, good, let's fix a bug here
 * 6. change sub view's parent between frameLayoutContainer and frameLayoutContainerOther
 * 7. let's run app, good
 *
 * thanks
 *
 * video url: https://youtu.be/8pLLqFMiNhg
 */
class MainActivity : AppCompatActivity() {

    private lateinit var frameLayoutContainer: FrameLayout
    private lateinit var frameLayoutContainerOther: FrameLayout

    private lateinit var subView: SubView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayoutContainer = findViewById(R.id.frameLayoutContainer)
        frameLayoutContainerOther = findViewById(R.id.frameLayoutContainerOther)

        findViewById<Button>(R.id.buttonChange).setOnClickListener {
            changeView()
        }

        addSubView()
    }

    private fun addSubView() {
        subView = SubView(this)
        frameLayoutContainer.addView(subView.view)
    }

    /**
     * I want to change subview's parent
     * move subview from frameLayoutContainer into frameLayoutContainerOther
     */
    private fun changeView() {

        if(frameLayoutContainer.childCount == 0) {

            // 1 remove subview from frameLayoutContainer
            frameLayoutContainerOther.removeView(subView.view)

            // 2 add subview into frameLayoutContainerOther
            frameLayoutContainer.addView(subView.view)

        }
        else if (frameLayoutContainerOther.childCount == 0) {

            // 1 remove subview from frameLayoutContainer
            frameLayoutContainer.removeView(subView.view)

            // 2 add subview into frameLayoutContainerOther
            frameLayoutContainerOther.addView(subView.view)

        }

    }
}