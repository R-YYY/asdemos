package com.example.rydemos.videodemo

import android.app.Application
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rydemos.util.RYLog
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

class VideoViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "VideoViewModel"
    }

    val exoPlayer = ExoPlayer.Builder(application.applicationContext).build()

    private val okHttpClient = OkHttpClient()

    private val context = application.applicationContext

    private val urls = listOf(
        "https://vjs.zencdn.net/v/oceans.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://vjs.zencdn.net/v/oceans.mp4",
        "https://vjs.zencdn.net/v/oceans.mp4"
    )

    init {
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                val stateString: String = when (playbackState) {
                    ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE ===1111"
                    ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING ===2222"
                    ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY ===3333"
                    ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED ===4444" //播放列表存在时播放最后一个播放完成才会回掉该方法
                    else -> "UNKNOWN_STATE ===0000"
                }
                RYLog.d(VideoActivity.TAG, "changed state to $stateString")
                if (playbackState == Player.STATE_ENDED) {
                    playRandomVideo()
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                playRandomVideo()
            }
        })
    }

    fun play() {
        playRandomVideo()
    }

    fun stop() {
        exoPlayer.stop()
    }

    private fun playRandomVideo() {
        val url = urls.random()
        viewModelScope.launch {
            kotlin.runCatching {
                val file = getCacheFile(url)
                if (isCacheValid(url, file)) {
                    playVideo(file)
                } else {
                    downloadAndPlay(url, file)
                }
            }
        }
    }

    private fun playVideo(file: File) {
        val uri = file.toUri()
        val mediaItem = MediaItem.fromUri(uri)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    private suspend fun downloadAndPlay(url: String, file: File) {
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val request = Request.Builder().url(url).build()
                val response = okHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let { body ->
                        val inputStream = body.byteStream()
                        val outputStream = FileOutputStream(file)

                        inputStream.use { input ->
                            outputStream.use { output ->
                                input.copyTo(output)
                            }
                        }
                    }

                    playVideo(file)
                } else {
                    playRandomVideo()
                }
            }.onFailure {
                RYLog.e(TAG, it.stackTraceToString())
                playRandomVideo()
            }
        }
    }

    private suspend fun getCacheFile(url: String): File {
        return withContext(Dispatchers.IO) {
            val fileName = url.substringAfterLast("/")
            File(context.cacheDir, fileName)
        }
    }

    private suspend fun isCacheValid(url: String, file: File): Boolean {
        return withContext(Dispatchers.IO) {
            if (!file.exists()) {
                return@withContext false
            }
            val request = Request.Builder().url(url).head().build()
            val response = okHttpClient.newCall(request).execute()

            if (!response.isSuccessful) {
                return@withContext false
            }

            val remoteFileSize =
                response.header("Content-Length")?.toLongOrNull() ?: return@withContext false
            val localFileSize = file.length()

            remoteFileSize == localFileSize
        }
    }

    fun onDestroy() {
        exoPlayer.release()
    }
}