package com.htk.sopt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.htk.sopt1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding :ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etHomename.setText("hotaek Kwak")
        binding.tvHomeid.text = intent.getStringExtra("id")
        binding.etHomeintroduce.setText("안드 사랑해 보고싶다. 안드 사랑 해보고싶다.")

    }
    override fun onStart() {
        super.onStart()
        Log.d("homechecklog", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("homechecklog", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("homechecklog", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("homechecklog", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("homechecklog", "onDestroy")
    }
}