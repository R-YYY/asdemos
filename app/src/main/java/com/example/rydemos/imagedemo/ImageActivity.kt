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
                ImageInfo("https://img1.baidu.com/it/u=2894078051,1898843685&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500"),
                ImageInfo("https://img1.baidu.com/it/u=2855127458,351801037&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"),
                ImageInfo("https://img0.baidu.com/it/u=4044989186,44219514&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"),
                ImageInfo("https://img1.baidu.com/it/u=3637490291,2814538424&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
            )
        )
    }

}