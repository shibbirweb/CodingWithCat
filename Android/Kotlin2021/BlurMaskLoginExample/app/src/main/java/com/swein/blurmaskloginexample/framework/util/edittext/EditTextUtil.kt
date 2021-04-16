package com.swein.blurmaskloginexample.framework.util.edittext

import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView

class EditTextUtil {

    companion object {

        fun jumpFocus(fromEditText: EditText, targetEditText: EditText) {

            fromEditText.setOnEditorActionListener { _: TextView?, _: Int, event: KeyEvent? ->

                if(event != null && KeyEvent.KEYCODE_ENTER == event.keyCode) {

                    if (event.action == KeyEvent.ACTION_UP) {
                        targetEditText.requestFocus()
                        return@setOnEditorActionListener true
                    }

                }
                return@setOnEditorActionListener true
            }
        }
    }
}