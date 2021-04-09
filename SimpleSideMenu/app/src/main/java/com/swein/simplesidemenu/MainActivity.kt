package com.swein.simplesidemenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

/**
 * let's build a simple side menu
 * 1. xml
 * 2. side menu fragment
 * 3. put side menu fragment into main xml
 * 4. let's run app, let's run again, good
 * 5. thanks
 */
class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var buttonMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        setListener()
    }

    private fun findView() {
        drawerLayout = findViewById(R.id.drawerLayout)
        buttonMenu = findViewById(R.id.buttonMenu)

        // this will disable swipe to open side menu
        // if you need swipe to open side menu, delete this
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun setListener() {
        buttonMenu.setOnClickListener {
            showSideMenu()
        }
    }

    private fun showSideMenu() {
        drawerLayout.openDrawer(GravityCompat.START, true)
    }


}