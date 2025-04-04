package com.example.rydemos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.model.LottieCompositionCache
import com.bumptech.glide.Glide
import com.example.rydemos.databinding.ActivityMainBinding
import com.example.rydemos.imagedemo.ImageActivity
import com.example.rydemos.scaledemo.ScaleActivity


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
    }
}
