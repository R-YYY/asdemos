package com.example.rydemos.util

import android.util.Log

object RYLog {
    private const val TAG = "ry-debug"

    fun d(tag: String?, msg: String?) {
        Log.d(TAG, "$tag $msg")
    }

    fun i(tag: String?, msg: String?) {
        Log.i(TAG, "$tag $msg")
    }

    fun w(tag: String?, msg: String?) {
        Log.w(TAG, "$tag $msg")
    }

    fun e(tag: String?, msg: String?) {
        Log.e(TAG, "$tag $msg")
    }
}