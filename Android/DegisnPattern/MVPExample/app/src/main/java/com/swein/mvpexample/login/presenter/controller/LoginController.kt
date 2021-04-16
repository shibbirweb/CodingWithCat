package com.swein.mvpexample.login.presenter.controller

import com.swein.mvpexample.framework.util.thread.ThreadUtil

object LoginController {

    interface LoginControllerDelegate {
        fun onSuccess(response: String)
        fun onFailed()
    }

    fun requestLogin(id: String, password: String, delegate: LoginControllerDelegate) {

        // send id and password to server and waiting response
        // let's suppose we need wait 3 second to fetch the data from server

        // start fetching data in thread
        ThreadUtil.startThread {

            // fetching data
            Thread.sleep(3000)

            // get response
            delegate.onSuccess("response from server, user info is a jsonObjectString, you need parsing it.")

        }

    }

}