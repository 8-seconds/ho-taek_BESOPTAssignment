package com.htk.sopt1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import com.htk.sopt1.HomeActivity
import com.htk.sopt1.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///LayoutInflater는 xml을 activity에 뿌려주는 다리와 같은 역할을 함.
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initButtonClickEvent()
        signUpButtonClickEvent()
    }

    private fun initButtonClickEvent() {
        binding.btLogin.setOnClickListener {
            val userId = binding.etId.text.toString()
            val userPassword = binding.etPassword.text.toString()
            // 값이 Null이거나 비어있을 경우 괄호 부분을 실행
            if (userId.isNullOrBlank() or userPassword.isNullOrBlank()) {
                Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT)
                        .show()
            } else {
                Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT)
                        .show()
                val intent1 = Intent(this@SignInActivity, HomeActivity::class.java)
                intent1.putExtra("id",userId)
                startActivity(intent1)
            }
        }

    }
    private fun signUpButtonClickEvent() {
        val launcher: ActivityResultLauncher<Intent> =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                    activityResult ->
                    if (activityResult.resultCode == RESULT_OK){
                        val name = activityResult.data?.getStringExtra("name")
                        val id = activityResult.data?.getStringExtra("id")
                        val password = activityResult.data?.getStringExtra("password")
                        binding.etId.setText(id)
                        binding.etPassword.setText(password)
                    }
                }

        binding.btSignup.setOnClickListener {
            launcher.launch(Intent(this@SignInActivity,SignUpActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("inchecklog", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("inchecklog", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("inchecklog", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("inchecklog", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("inchecklog", "onDestroy")
    }




}





