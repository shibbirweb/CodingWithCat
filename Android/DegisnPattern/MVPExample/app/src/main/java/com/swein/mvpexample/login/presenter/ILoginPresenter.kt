package com.swein.mvpexample.login.presenter

interface ILoginPresenter {

    fun clear()
    fun showProgress()
    fun hideProgress()
    fun login(id: String, password: String)
}