package com.example.rydemos

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.rydemos.databinding.ActivityTestBinding

class TestActivity : ComponentActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}