package com.example.rydemos.videodemo

import android.content.Context
import android.util.AttributeSet
import androidx.core.net.toUri
import com.example.rydemos.util.RYLog
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

class CustomPlayerView : PlayerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var videoUrl = ""

    private val TAG = "CustomPlayerView_${hashCode()}"

    init {
        player = ExoPlayerManager.getSimpleExoPlayer()
        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                val stateString: String = when (playbackState) {
                    ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE ===1111"
                    ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING ===2222"
                    ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY ===3333"
                    ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED ===4444" //播放列表存在时播放最后一个播放完成才会回掉该方法
                    else -> "UNKNOWN_STATE ===0000"
                }
                RYLog.d(TAG, "changed state to $stateString")
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                super.onPlayWhenReadyChanged(playWhenReady, reason)
                if (playWhenReady) {
                    VideoManager.playNew(player)
                }
                RYLog.i(TAG, "playWhenReady:${playWhenReady}, reason:${reason}")
            }
        })
    }

    fun setUrl(url: String) {
        kotlin.runCatching {
            if (videoUrl == url) {
                return
            }
            if (videoUrl.isNotEmpty()) {
                player?.clearMediaItems()
            }
            videoUrl = url
            val mediaItem = MediaItem.fromUri(url.toUri())
            player?.setMediaItem(mediaItem)
            player?.prepare()
        }
    }

    fun start() {
        if (player?.playWhenReady != true) {
            player?.playWhenReady = true
        }
    }

    fun pause() {
        kotlin.runCatching {
            if (videoUrl.isNotEmpty()) {
                player?.playWhenReady = false
            }
        }
    }

    fun stop() {
        kotlin.runCatching {
            player?.stop()
            player?.release()
            videoUrl = ""
        }
    }
}