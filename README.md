### Level1: 안린이 탈출하기

(1) - Postman 테스트 사진

- 로그인

![로그인_postman](https://user-images.githubusercontent.com/71322949/118225793-b8147100-b4c0-11eb-928b-4b3442db62f2.png)

- 회원가입

  ![회원가입_postman](https://user-images.githubusercontent.com/71322949/118225932-f7db5880-b4c0-11eb-913c-d1764ce1da5b.png)



(2) 회원가입 -> 로그인

<img src="https://user-images.githubusercontent.com/71322949/118235124-4394fe80-b4cf-11eb-9cf9-f414f5c43522.png" alt="Screenshot_1620976002" style="zoom:25%;" /><img src="https://user-images.githubusercontent.com/71322949/118235143-4ee82a00-b4cf-11eb-9b9d-8987f9847f75.png" alt="Screenshot_1620976028" style="zoom:25%;" />



<img src="https://user-images.githubusercontent.com/71322949/118235187-62939080-b4cf-11eb-8636-baf74eda617f.png" alt="Screenshot_1620976031" style="zoom:25%;" /><img src="https://user-images.githubusercontent.com/71322949/118235212-6a533500-b4cf-11eb-8e61-85b5d6b353a6.png" alt="Screenshot_1620976038" style="zoom:25%;" />

- manifest에 인터넷 permission하기

  ```
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="com.htk.assginment2">
  
      <uses-permission android:name="android.permission.INTERNET"/>
  ```



- Retrofit Interface 설계! : 서버에 요청을 보내고 받는 상호작용 방법의 정의

  ```
  interface SoptService {
      @POST("/login/signin")		
      fun postLogin(
          @Body body: RequestLoginData
      ) : Call<ResponseLoginData>
  
      @POST("/login/signup")
      fun postSignUp(
          @Body body: RequestSignUpData
      ): Call<ResponseSignUpData>
  }
  ```

  

- Request 객체 설계 (Login Data)

  ```
  data class RequestLoginData(
      @SerializedName("email")
      val id: String,
      val password: String,
  )
  ```
  
  
  
- Request 객체 설계 (Sign Up Data)

  ```
  data class RequestSignUpData(
      @SerializedName("email")
      val id : String,
      val password : String,
      val nickname : String,
      val sex : String,
      val phone : String,
      val birth : String
  )
  ```
  
  
  
- Response 객체 설계(Login Data)

  ```
  data class ResponseLoginData(
      val success: Boolean,
      val message: String,
      val data: Data?
  ) {
      data class Data(
          @SerializedName("UserId")
          val userId: Int,
          val user_nickname: String,
          val token: String
      )
  }
  ```
  
  
  
- Response 객체 설계(Sign up Data)

  ```
  data class ResponseSignUpData(
      val success: Boolean,
      val message: String,
      val data: Data?
  ){
      data class Data(
      val nickname : String
      )
  }
  ```
  
  
  
- Retrofit Interface 구현체 만들기(Sigleton 패턴)

  ```
  object ServiceCreator {
      private const val BASE_URL = "http://cherishserver.com"
  
      private val retrofit: Retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
  
      val soptService : SoptService=retrofit.create(SoptService::class.java)
  }
  ```
  
  

  -  callback 등록! (Login)

  ```
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
  ```

- callback 등록 (회원가입)

```
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
```



