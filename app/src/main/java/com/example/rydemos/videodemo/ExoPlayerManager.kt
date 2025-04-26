package com.example.rydemos.videodemo

import com.example.rydemos.MainApplication
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.upstream.DefaultAllocator
import okhttp3.OkHttpClient

object ExoPlayerManager {
    private const val TAG = "ExoPlayerManager"

    private val okHttpDataSourceFactory by lazy {
        val client = OkHttpClient.Builder().build()
        OkHttpDataSource.Factory(client)
    }

    fun getSimpleExoPlayer(): ExoPlayer {
        val context = MainApplication.mApplicationContext
        val loadControl = DefaultLoadControl.Builder()
            .setAllocator(DefaultAllocator(true, 16 * 16 * 16 * 16))
            .setBufferDurationsMs(25000, 50000, 5000, 5000)
            .setPrioritizeTimeOverSizeThresholds(false)
            .setTargetBufferBytes(-1)
            .build()
        return SimpleExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(okHttpDataSourceFactory))
            .setLoadControl(loadControl)
            .build()
    }
}