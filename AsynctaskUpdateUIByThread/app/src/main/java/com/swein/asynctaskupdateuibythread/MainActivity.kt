package com.swein.asynctaskupdateuibythread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.swein.asynctaskupdateuibythread.frameword.util.ThreadUtil

/**
 * How to use asynctask to update ui by thread step by step
 * 1. let's go, first, build ui
 * 2. run app, ok
 * 3. I'll update 3 textView one by one.
 * for example I'll update textViewOne's text after 2 seconds,
 * and then I'll update textViewTwo's text after 3 second
 *
 * 4. ok, let's build thread tool
 * 5. ok , let's update ui
 * 6. run app, sorry...sorry again
 * 7. good, let's try other test
 * 8. thanks
 */
class MainActivity : AppCompatActivity() {

    private lateinit var textViewOne: TextView
    private lateinit var textViewTwo: TextView
    private lateinit var textViewThree: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()

//        updateViewTestOne()
        updateViewTestTwo()
    }

    private fun findView() {
        textViewOne = findViewById(R.id.textViewOne)
        textViewTwo = findViewById(R.id.textViewTwo)
        textViewThree = findViewById(R.id.textViewThree)
    }

    // textViewOne will be updated after 2 seconds, and then, textViewTwo will be updated after 2 seconds,
    // and finally, textViewThree will be updated after 1 second
    private fun updateViewTestTwo() {

        ThreadUtil.startThread {

            ThreadUtil.startUIThread(2000) {
                textViewOne.text = "Hello, update one"



                ThreadUtil.startThread {

                    Thread.sleep(2000)

                    ThreadUtil.startUIThread(0) {
                        textViewTwo.text = "Hello, update two"



                        ThreadUtil.startThread {

                            Thread.sleep(1000)

                            ThreadUtil.startUIThread(0) {
                                textViewThree.text = "Hello, update three"
                            }
                        }
                    }
                }
            }
        }

    }

    // after three seconds, 3 textView will be updated almost same time, and Thread.sleep will not block the ui,
    // so we can scroll up or scroll down.
    private fun updateViewTestOne() {

        ThreadUtil.startThread {

            Thread.sleep(3000)

            ThreadUtil.startUIThread(0) {
                textViewOne.text = "Hello, update one"
            }
        }

        ThreadUtil.startThread {

            Thread.sleep(3000)

            ThreadUtil.startUIThread(0) {
                textViewTwo.text = "Hello, update two"
            }
        }

        ThreadUtil.startThread {

            Thread.sleep(3000)

            ThreadUtil.startUIThread(0) {
                textViewThree.text = "Hello, update three"
            }
        }
    }
}