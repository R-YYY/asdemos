package com.example.rydemos.util

import android.text.TextUtils
import android.view.View
import java.util.Locale

object Utils {
    fun isRTL(): Boolean {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL
    }
}