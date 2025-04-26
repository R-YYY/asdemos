package com.example.rydemos.videodemo

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rydemos.databinding.ActivityVideoBinding
import com.example.rydemos.databinding.ItemVideoBinding
import com.example.rydemos.util.RYLog
import kotlin.math.abs

class VideoActivity : ComponentActivity() {

    companion object {
        const val TAG = "VideoActivity"
        const val MP4_URL = "https://media.w3.org/2010/05/sintel/trailer.mp4"
    }

    private lateinit var mBinding: ActivityVideoBinding

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initRV()
    }

    private fun initRV() {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = VideoAdapter()
        mBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RYLog.i(TAG, "==== onScrollStateChanged")
                    mHandler.postDelayed(updatePlayingVideo, 100)
                }
            }
        })
    }

    private val updatePlayingVideo = Runnable {
        val layoutManager =
            mBinding.recyclerView.layoutManager as? LinearLayoutManager ?: return@Runnable
        val first = layoutManager.findFirstVisibleItemPosition()
        val last = layoutManager.findLastVisibleItemPosition()
        if (first == -1 || last == -1) {
            return@Runnable
        }

        val recyclerCenterY: Int = mBinding.recyclerView.height / 2
        var closestPosition = -1
        var minDistance = Int.MAX_VALUE

        for (i in first..last) {
            val view = layoutManager.findViewByPosition(i)
            val viewHolder = view?.let { mBinding.recyclerView.getChildViewHolder(it) }
            if (viewHolder is VideoAdapter.VideoViewHolder) {
                val viewCenterY = (view.top + view.bottom) / 2
                val distance = abs(viewCenterY - recyclerCenterY)
                if (distance < minDistance || (distance == minDistance && i < closestPosition)) {
                    minDistance = distance
                    closestPosition = i
                }
            }
        }

        if (closestPosition != -1) {
            (mBinding.recyclerView.adapter as? VideoAdapter)?.setCurrentPlayPosition(closestPosition)
        }
    }

    class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
        private var shouldPlayPosition = -1

        private val dataSet = arrayListOf<String>().apply {
            repeat(5) { add(MP4_URL) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
            return VideoViewHolder(
                ItemVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
            holder.onBindData(
                position == shouldPlayPosition,
                dataSet.getOrElse(position) { MP4_URL }, position
            )
        }

        fun setCurrentPlayPosition(newPos: Int) {
            if (newPos == shouldPlayPosition) {
                return
            }
            val oldPos = shouldPlayPosition
            shouldPlayPosition = newPos
            RYLog.i(TAG, "oldPos=${oldPos},newPos=${newPos}")
            if (oldPos != -1) {
                notifyItemChanged(oldPos)
            }
            if (newPos != -1) {
                notifyItemChanged(newPos)
            }
        }

        class VideoViewHolder(private val mBinding: ItemVideoBinding) : ViewHolder(mBinding.root) {

            fun onBindData(shouldPlay: Boolean, url: String, position: Int) {
                mBinding.tvUp.text = "${repeat(10) { position }}↓↓↓↓↓↓↓↓↓"
                mBinding.tvDown.text = "${repeat(10) { position }}↑↑↑↑↑↑↑↑↑"
                mBinding.playerView.setUrl(url)
                if (shouldPlay) {
                    mBinding.playerView.start()
                } else {
                    mBinding.playerView.pause()
                }
            }

//            fun onBindData(
//                shouldPlay: Boolean,
//                url: String,
//                position: Int,
//                payloads: MutableList<Any>
//            ) {
//                mBinding.tvUp.text = "${repeat(10) { position }}↓↓↓↓↓↓↓↓↓"
//                mBinding.tvDown.text = "${repeat(10) { position }}↑↑↑↑↑↑↑↑↑"
//                if (shouldPlay) {
//                    mBinding.playerView.start()
//                }
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.recyclerView.clearOnScrollListeners()
    }
}