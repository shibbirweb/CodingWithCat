package com.swein.simplecoroutineexampleforbeginner.caseperfect

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import com.swein.simplecoroutineexampleforbeginner.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * a perfect coroutine use case
 * 1. let's build a Basic activity class for coroutine
 * 2. let's make a get data function
 * let's run app, good
 *
 * use coroutine that you can asynchronous UI without callback!!
 *
 * next video, let's build use case about kotlin coroutine fetch data with okhttp open source and not use callback!
 * thanks and see you next video
 *
 */
class CasePerfectActivity : BasicCoroutineActivity() {

    private lateinit var root: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_perfect)

        root = findViewById(R.id.root)

        findViewById<Button>(R.id.buttonGet).setOnClickListener {

            requestData()

        }
    }

    private fun requestData() {

        // start a global scope on main
        // now you can just use launch without GlobalScope
        launch(Dispatchers.Main) {

            val time = measureTimeMillis {

                val data1Result = async {
                    getData("data1", 1000)
                }

                val data2Result = async {
                    getData("data2", 600)
                }

                val data3Result = async {
                    getData("data3", 800)
                }

                // continue to here after get data1 and data2
                // in this case, this time should be 1000 ms or same as 1000 ms
                Log.d("case perfect result", "${data1Result.await()} ${data2Result.await()} ${data3Result.await()}")

                Log.d("case perfect result", "update UI")
                root.setBackgroundColor(Color.CYAN)
            }

            Log.d("case perfect", "case one take time: ${time}ms")
        }

    }

    private suspend fun getData(str: String, delay: Long) = withContext(Dispatchers.IO) {
        delay(delay)
        return@withContext "data $str"
    }
}