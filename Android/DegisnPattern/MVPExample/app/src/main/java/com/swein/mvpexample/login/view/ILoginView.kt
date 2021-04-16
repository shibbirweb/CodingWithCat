package com.swein.mvpexample.login.view

interface ILoginView {

    // I need show progress, hide progress, clear input, update login info.

    fun onClear()
    fun onShowProgress()
    fun onHideProgress()
    fun onUpdateLoginResultUserInfo(nickname: String, age: Int)

}