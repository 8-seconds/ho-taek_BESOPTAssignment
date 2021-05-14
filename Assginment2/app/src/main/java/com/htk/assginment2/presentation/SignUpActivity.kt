package com.htk.assginment2.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.htk.assginment2.api.ServiceCreator
import com.htk.assginment2.data.request.RequestSignUpData
import com.htk.assginment2.data.response.ResponseSignUpData
import com.htk.assginment2.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupButtonClickEvent()
    }


    private fun initSignupButtonClickEvent() {
        binding.btRegister.setOnClickListener {
            val requestSignUpData = RequestSignUpData(
                id = binding.etSignId.text.toString(),
                nickname = binding.etName.text.toString(),
                password = binding.etSignPassword.text.toString(),
                sex = "0",
                phone = "010-3368-4293",
                birth = "1997.07.07"

            )

            val call : Call<ResponseSignUpData> = ServiceCreator.soptService
                .postSignUp(requestSignUpData)
            call.enqueue(object: Callback<ResponseSignUpData>{
                override fun onResponse(
                    call: Call<ResponseSignUpData>,
                    response: Response<ResponseSignUpData>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        setLogin()
                    } else{
                        Toast.makeText(this@SignUpActivity, "다시 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                    Log.d("NetworkTest", "error:${t}")
                }
            })
        }
    }




    private fun setLogin(){
        val name = binding.etName.text.toString()
        val id = binding.etSignId.text.toString()
        val password = binding.etSignPassword.text.toString()
        if (name.isNullOrBlank() or id.isNullOrBlank() or password.isNullOrBlank()) {
            //꼭 context 쓸때 액티비티이름 같이 적어주기!!!!
            Toast.makeText(this@SignUpActivity, "빈 칸이 있는지 확인해주세요", Toast.LENGTH_SHORT)
                .show()
        }else {
            val intent = Intent()
            intent.putExtra("id", id)
                .putExtra("password", password)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("upchecklog", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("upchecklog", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("upchecklog", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("upchecklog", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("upchecklog", "onDestroy")
    }
}