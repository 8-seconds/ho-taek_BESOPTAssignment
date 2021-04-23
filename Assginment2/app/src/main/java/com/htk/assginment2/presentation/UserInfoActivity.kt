package com.htk.assginment2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.htk.assginment2.R

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val follwingFragment = FollowingFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.user_info_fragment, FollowingFragment())
        transaction.commit()
    }
}