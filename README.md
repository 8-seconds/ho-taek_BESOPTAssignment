1. 자동 로그인 구현 과정

   (1) ID/PW가 있는 경우 자동 로그인

   ```
    private fun searchUserAuthStorage() {
           if (SoptUserAuthStorage.hasUserData()) {
               SoptUserAuthStorage.getUserId()
               SoptUserAuthStorage.getUserPw()
               startHomeActivity()
               toast("자동 로그인 성공!")
           }
       }
   ```

   

   (2) 로그인 시에 sharedpreference에 집어 넣기.

   ```
    private fun requestLogin(requestLoginData: RequestLoginData) {
   
   
           val call: Call<ResponseLoginData> = ServiceCreator.soptService
               .postLogin(requestLoginData)
           call.enqueue(object : Callback<ResponseLoginData> {
               override fun onResponse(
                   call: Call<ResponseLoginData>,
                   response: Response<ResponseLoginData>
               ) {
                   if (response.isSuccessful) {
                       val data = response.body()?.data
                       toast(data?.user_nickname)
   
                       if (!SoptUserAuthStorage.hasUserData()) {
                           SoptUserAuthStorage.saveUserId(requestLoginData.id)
                           SoptUserAuthStorage.saveUserPW(requestLoginData.password)
                       }
                       startHomeActivity()
                   } else {
                       toast("등록되지 않은 회원입니다.")
                   }
               }
   ```

   (3) 로그아웃 기능! sharedpreference clear 하기.

   ```
   private fun clickLogoutButton(){
           binding.btLogout.setOnClickListener {
               SoptUserAuthStorage.clearUserData()
               Log.d("hello",SoptUserAuthStorage.getUserId())
               backSignInActivity()
           }
       }
   ```

   

1-2. object로 구현한 SharedPreference

```
object SoptUserAuthStorage {
    private const val STORAGE_KEY = "user_auth"
    private const val USER_ID = "Id"
    private const val USER_PW = "password"
    private lateinit var sharedPreference : SharedPreferences

    fun initSharedPreference(context : Context) {
            sharedPreference = context.getSharedPreferences(
                "$(context.packageName).$STORAGE_KEY",
                Context.MODE_PRIVATE)
    }

    fun saveUserId(id: String){
        sharedPreference.edit()
            .putString(USER_ID, id)
            .apply()
    }

    fun saveUserPW(pw: String){
        sharedPreference.edit()
            .putString(USER_PW, pw)
            .apply()
    }

    fun getUserId(): String{
        return sharedPreference.getString(USER_ID,"") ?: ""
    }

    fun getUserPw(): String{
        return sharedPreference.getString(USER_PW,"") ?: ""
    }

    fun hasUserData() : Boolean {
        return !sharedPreference.getString(USER_ID,"").isNullOrBlank()
                && !sharedPreference.getString(USER_PW,"").isNullOrBlank()
    }

    fun clearUserData() {
        sharedPreference.edit()
            .clear()
            .apply()
    }
}
```

 

```
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initSharedPreference(this)
    }
}
```

Application 클래스를 사용하여 어플리케이션 사이의 컴포넌트들이 어디서든 context를 이용한 접근이 가능하도록 하여 위의 sharedpreference 코드를 간소화 하였다.





1-3. week7 세미나를 하며 배운점.

sharedpreference에 대해서 조금이나마 알 수 있게 되었고 코드를 간소화 하는 과정에서 Application 클래스를 활용하는 방법을 알 수 있었습니다!