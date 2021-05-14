package com.htk.assginment2.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.htk.assginment2.api.ServiceCreator
import com.htk.assginment2.data.request.RequestLoginData
import com.htk.assginment2.data.response.ResponseLoginData
import com.htk.assginment2.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///LayoutInflater는 xml을 activity에 뿌려주는 다리와 같은 역할을 함.
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginButtonClickEvent()
        signUpButtonClickEvent()
    }

    private fun loginButtonClickEvent() {
        binding.btLogin.setOnClickListener {
            val requestLoginData = RequestLoginData(
                id = binding.etId.text.toString(),
                password = binding.etPassword.text.toString()
            )

            val call: Call<ResponseLoginData> = ServiceCreator.soptService
                .postLogin(requestLoginData)
            call.enqueue(object: Callback<ResponseLoginData>{
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()?.data
                        Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT).show()

                        startHomeActivity()
                    }else{
                        Toast.makeText(this@SignInActivity, "등록되지 않은 회원입니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    Log.d("NetworkTest", "error:${t}")
                }
            })
        }

    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
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
            launcher.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
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