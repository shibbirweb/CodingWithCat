package com.swein.datastoraexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Do you still use shared preferecnes????? no no no.
 * let's use data store in kotlin with coroutine
 *
 * 1. project setting
 * 2. xml
 * 3. data store manager
 * 4. let's test
 * 5. let's run app
 * - if I just click get button before I save value, than the edit text will show '123'
 * - again
 * - let's save value
 * - good, thanks
 */
class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {

            val text = editText.text.toString().trim()
            if (text == "") {
                return@setOnClickListener
            }

            GlobalScope.launch {
                // don't forget "@MainActivity"
                DataStoreManager.saveValue(this@MainActivity, "testKey", text)
            }

        }

        findViewById<Button>(R.id.buttonGet).setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                // don't forget "@MainActivity"
                DataStoreManager.getStringValue(this@MainActivity, "testKey")
                // or
                val value = DataStoreManager.getStringValue(this@MainActivity, "testKey", default = "123")

                GlobalScope.launch(Dispatchers.Main) {
                    editText.setText(value)
                }
            }

        }
    }
}