package com.swein.simplecoroutineexampleforbeginner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.swein.simplecoroutineexampleforbeginner.caseperfect.CasePerfectActivity

/**
 * Hello
 *
 * Is coroutine in Kotlin difficult to use? No !!!
 *
 * In this video, let's test examples case by case
 * After that you can easily use coroutine.
 * Let's go
 *
 * the example is when I click a button, after xxxx ms, than update my UI
 *
 * youtube video url: https://youtu.be/7ryL5n3D1Q4
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonCaseOne).setOnClickListener {
            startActivity(Intent(this, CaseOneActivity::class.java))
        }

        findViewById<Button>(R.id.buttonCaseTwo).setOnClickListener {
            startActivity(Intent(this, CaseTwoActivity::class.java))
        }

        findViewById<Button>(R.id.buttonCasePerfect).setOnClickListener {
            startActivity(Intent(this, CasePerfectActivity::class.java))
        }
    }
}