package com.swein.asynctaskupdateuibythread.frameword.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

class ThreadUtil {

    companion object {

        private val executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        private val handler = Handler(Looper.getMainLooper())

        // start task with thread
        fun startThread(runnable: Runnable) {
            executorService.submit(runnable)
        }

        // back to main thread to update UI
        fun startUIThread(delayMillis: Int, runnable: Runnable) {
            handler.postDelayed(runnable, delayMillis.toLong())
        }
    }
}