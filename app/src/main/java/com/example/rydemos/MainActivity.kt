package com.example.rydemos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.rydemos.databinding.ActivityMainBinding
import com.example.rydemos.gifdemo.GifActivity
import com.example.rydemos.imagedemo.ImageActivity
import com.example.rydemos.motiondemo.MotionActivity
import com.example.rydemos.scaledemo.ScaleActivity
import com.example.rydemos.videodemo.VideoActivity


class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btnImage.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, ImageActivity::class.java)
            startActivity(intent)
        }
        mBinding.btnScale.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, ScaleActivity::class.java)
            startActivity(intent)
        }
        mBinding.btnGif.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, GifActivity::class.java)
            startActivity(intent)
        }
        mBinding.btnMotion.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, MotionActivity::class.java)
            startActivity(intent)
        }
        mBinding.btnVideo.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, VideoActivity::class.java)
            startActivity(intent)
        }
    }
}
