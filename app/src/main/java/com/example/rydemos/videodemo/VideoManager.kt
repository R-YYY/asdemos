package com.example.rydemos.videodemo

import com.google.android.exoplayer2.Player

object VideoManager {

    private var currentPlayer: Player? = null

    fun playNew(newPlayer: Player?) {
        if (newPlayer == currentPlayer) return
        currentPlayer?.apply {
            pause()
        }
        currentPlayer = newPlayer
    }

    fun release() {
        currentPlayer?.release()
        currentPlayer = null
    }

    fun release(player: Player) {
        if (player != currentPlayer) return
        currentPlayer?.release()
        currentPlayer = null
    }
}