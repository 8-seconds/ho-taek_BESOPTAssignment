package com.htk.assginment2.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.htk.assginment2.R
import com.htk.assginment2.data.SoptUserAuthStorage
import com.htk.assginment2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        homeBind()
        homeFragmentLink()
        moreButtonClickEvent()
        clickLogoutButton()
    }

    private fun clickLogoutButton(){
        binding.btLogout.setOnClickListener {
            SoptUserAuthStorage.clearUserData()
            Log.d("hello",SoptUserAuthStorage.getUserId())
            backSignInActivity()
        }
    }


    private fun homeBind(){
        with(binding) {
            tvHomename.text = "hotaek kwak"
            tvHomeid.text = intent.getStringExtra("id")
            tvHomeintroduce.text = "안드 사랑해 보고싶다. 안드 사랑 해보고싶다."
        }
    }

    private fun backSignInActivity(){
        val intent = Intent(this@HomeActivity, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun homeFragmentLink(){
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.home_fragment, homeFragment)
        transaction.commit()
    }


    private fun moreButtonClickEvent() {
        binding.btUserInfo.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }
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