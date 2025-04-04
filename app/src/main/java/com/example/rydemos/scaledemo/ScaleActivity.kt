package com.example.rydemos.scaledemo

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.activity.ComponentActivity
import com.example.rydemos.databinding.ActivitySacleBinding

class ScaleActivity : ComponentActivity() {

    companion object {
        const val TAG = "ScaleActivity"
    }

    private lateinit var binding: ActivitySacleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySacleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.originView.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.originView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val newWidth = binding.originView.width * 288f / 264f
                binding.frame.layoutParams = binding.frame.layoutParams.apply {
                    width = newWidth.toInt()
                }
                binding.frame.visibility = View.VISIBLE
            }
        })
    }
}