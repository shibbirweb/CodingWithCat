package com.swein.mvpexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.swein.mvpexample.login.view.LoginActivity

/**
 * Hello, let's build a simple login screen by MVP pattern
 * 1. clear theme
 * 2. let's build view and view's interface. Before that, let's see what is mvp, ok
 * 3. xml
 * 4. let's build login's xml
 * 5. let's build view's interface. I need show progress, hide progress, clear input, update login info. let's build model first
 * 6. login activity should implement ILoginView
 * 7. let's see the MVP image again. we have model, and view, now, we need presenter and presenter's interfacee
 * 8. In presenter, need make view's interface to do something. clear, show progress, hide progress, and view need presenter to login
 * 9. we need a controller to fetch the data from server. we don't need that because I'll make a dummy data.
 *
 * 10. view must holding presenter's interface, but view can not hold any model!!! this is very important, let's check login activity, none of model
 * 11. let view hold the presenter's interface
 *
 * 12. ok, let's run the app
 *
 * sorry....let's check code, run again, good!!! thanks
 * after click login button, I want to clear the input, run again, good, thanks
 *
 * youtube video: https://youtu.be/7aEvmkGigOo
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}