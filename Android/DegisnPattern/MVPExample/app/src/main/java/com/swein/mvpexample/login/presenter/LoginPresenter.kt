package com.swein.mvpexample.login.presenter

import android.util.Log
import com.swein.mvpexample.framework.util.thread.ThreadUtil
import com.swein.mvpexample.login.model.UserInfoModel
import com.swein.mvpexample.login.presenter.controller.LoginController
import com.swein.mvpexample.login.view.ILoginView

class LoginPresenter(var iLoginView: ILoginView): ILoginPresenter {

    override fun clear() {
        iLoginView.onClear()
    }

    override fun showProgress() {
        iLoginView.onShowProgress()
    }

    override fun hideProgress() {
        iLoginView.onHideProgress()
    }

    override fun login(id: String, password: String) {

        clear()

        showProgress()

        LoginController.requestLogin(id = id, password = password, object: LoginController.LoginControllerDelegate {
            override fun onSuccess(response: String) {
                Log.d("???", "onSuccess $response")

                // let's suppose parsing data here
                // I just make a dummy data

                val userInfoModel = UserInfoModel()
                userInfoModel.nickname = "Coding with cat"
                userInfoModel.age = 1

                // back to UI thread
                ThreadUtil.startUIThread(0) {
                    hideProgress()

                    iLoginView.onUpdateLoginResultUserInfo(nickname = userInfoModel.nickname, age = userInfoModel.age)
                }
            }

            override fun onFailed() {
                Log.d("???", "onFailed")

                hideProgress()
            }

        })
    }

}