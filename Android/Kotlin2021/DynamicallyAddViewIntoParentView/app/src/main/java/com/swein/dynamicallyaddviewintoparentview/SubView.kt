package com.swein.dynamicallyaddviewintoparentview

import android.content.Context
import android.view.LayoutInflater
import android.view.View

class SubView(context: Context) {

    var view: View = LayoutInflater.from(context).inflate(R.layout.view_sub, null, false)

}