package com.swein.simplecoroutineexampleforbeginner

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * let's run app
 * update UI after get data
 * good
 * ok, in next case we will get data1 and data2 in same time
 */
class CaseOneActivity : AppCompatActivity() {

    private lateinit var root: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_one)

        root = findViewById(R.id.root)

        findViewById<Button>(R.id.buttonGet).setOnClickListener {

//            // start a global scope
//            GlobalScope.launch(Dispatchers.IO) {
//                // launch on what ?? on IO
//
//                val time = measureTimeMillis {
//                    val data1 = "data1"
//                    delay(1000)
//                    val data2 = "data2"
//                    delay(600)
//
//                    Log.d("case one", "$data1 $data2")
//                }
//
//                // this is not a good case, but let's finish this first
//                GlobalScope.launch(Dispatchers.Main) {
//                    root.setBackgroundColor(Color.CYAN)
//                }
//                Log.d("case one", "case one take time: ${time}ms")
//            }

            // start a global scope on main
            GlobalScope.launch(Dispatchers.Main) {

                val time = measureTimeMillis {

                    // get data on IO
                    val data1 = withContext(Dispatchers.IO) {
                        delay(1000)
                        return@withContext "data1"
                    }

                    // get data on IO
                    val data2 = withContext(Dispatchers.IO) {
                        delay(600)
                        return@withContext "data2"
                    }

                    // continue to here after get data1 and data2
                    Log.d("case one", "$data1 $data2")
                    root.setBackgroundColor(Color.CYAN)


                }

                Log.d("case one", "case one take time: ${time}ms")
            }

        }


    }
}