package com.example.rydemos.imagedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rydemos.databinding.ActivityImageBinding

class ImageActivity : ComponentActivity() {
    private lateinit var mBinding: ActivityImageBinding

    private var mAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        mAdapter.setData(
            arrayListOf(
                ImageInfo("https://img2.baidu.com/it/u=1028011339,1319212411&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313"),
                ImageInfo("https://img2.baidu.com/it/u=1028011339,1319212411&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313"),
                ImageInfo("https://img2.baidu.com/it/u=1028011339,1319212411&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313"),
                ImageInfo("https://img2.baidu.com/it/u=1028011339,1319212411&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313"),
                ImageInfo("https://img2.baidu.com/it/u=1028011339,1319212411&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313")
            )
        )
    }

}