package com.example.rydemos.scaledemo

import android.os.Bundle
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
    }
}