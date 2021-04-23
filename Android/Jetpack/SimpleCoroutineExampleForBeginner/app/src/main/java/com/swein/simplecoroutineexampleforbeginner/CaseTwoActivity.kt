package com.swein.simplecoroutineexampleforbeginner

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * let's launch get data1 and data2 in same time
 * let's run app, good
 * 1022ms is far away from 1600 ms, that's means get data1 and get data2 launch in same time
 *
 * in next case, we will build a perfect coroutine use case
 */
class CaseTwoActivity : AppCompatActivity() {

    private lateinit var root: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_two)

        root = findViewById(R.id.root)

        findViewById<Button>(R.id.buttonGet).setOnClickListener {

            // start a global scope on main
            GlobalScope.launch(Dispatchers.Main) {

                val time = measureTimeMillis {

                    val data1Result = async {
                        // get data on IO
                        withContext(Dispatchers.IO) {
                            delay(1000)
                            return@withContext "data1"
                        }
                    }

                    val data2Result = async {

                        // get data on IO
                        withContext(Dispatchers.IO) {
                            delay(600)
                            return@withContext "data2"
                        }

                    }

                    // continue to here after get data1 and data2
                    // in this case, this time should be 1000 ms or same as 1000 ms
                    Log.d("case one", "${data1Result.await()} ${data2Result.await()}")
                    root.setBackgroundColor(Color.CYAN)


                }

                Log.d("case one", "case one take time: ${time}ms")
            }

        }

    }
}