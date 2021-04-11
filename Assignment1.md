<u>Level1:  안린이 탈출!</u>

### 1. 화면 전환후 데이터를 가져온 로직 정리

- SiggnInActivity -> SignUpActivity

  ```
   private fun signUpButtonClickEvent() {
          val launcher: ActivityResultLauncher<Intent> =              registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
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
  ```

  

- SignUpActivity -> SignInActivity

  ```
  if (name.isNullOrBlank() or id.isNullOrBlank() or password.isNullOrBlank()) {
                  //꼭 context 쓸때 액티비티이름 같이 적어주기!!!!
                  Toast.makeText(this@SignUpActivity, "빈 칸이 있는지 확인해주세요", Toast.LENGTH_SHORT)
                          .show()
              }else {
                  val intent = Intent()
                  intent.putExtra("id", id)
                  intent.putExtra("password", password)
                  setResult(Activity.RESULT_OK, intent)
                  finish()
  ```



- SignInActivity -> HomeActivity

  ```
   if (userId.isNullOrBlank() or userPassword.isNullOrBlank()) {
                  Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT)
                          .show()
              } else {
                  Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT)
                          .show()
                  val intent1 = Intent(this@SignInActivity, HomeActivity::class.java)
                  intent1.putExtra("id",userId)
                  startActivity(intent1)
  ```

  

### 2. 생명주기 호출 후 다른 엑티비티를 호출하면 어떻게 되는지 로그 캡쳐

- SignInActivity에서 SignUpActivity로 이동했을 때 onPause부터 onStop까지 발생한다.

![](C:\Users\kht07\OneDrive\솝트\생명주기Log.png)



### 3. 이번 과제를 통해 배운 내용!

- 이번 과제를 하면서 화면 전환 및 간단한 데이터를 보낼 수 있는 registerForActivity의 사용법을 배웠고, 

  모르는 것을 찾아보면서 MVP모델 및 기존에 사용하는 MVC모델 에 대해서도 잠깐이나마 볼 수 있었다.  과제하면서 너무 재밌었다!



<u> Level2: 안청년 탈출!</u>

- 변수 이름 체크하기  (x)

- constraintLayout 마스터하기! (x)

- ScrollView 만들기 : (o)



Level3: 나도 안드 고수를 향해서!!

- ViewBinding의 이름의 뜻

  제 생각에는 View(뷰) + Binding(묶어주는 것)!! View를 액티비티에 묶어주는것? 그러니까 View를 객체화하여서 우리가 액티비티에서 View를 참조할 수 있게 해주는 것이다. 기존에는 findViewbyid를 썼지만 ViewBinding을 쓰면 View를 참조하기 편해진다.

- 객체지향 어느 정도 다뤄보셨나요?

  Activity는 사용자 인터페이스 화면을 구성하는 컴포넌트라고 한다. 무슨 말인지 몰라서 헷갈려서

  사용자 인터페이스는 사람들이 컴퓨터와 상호작용하는 시스템이라고한다. 예시를 들면 에어컨의

  사용자 인터페이스는 에어컨 리모컨이다.  그냥 간단하게 생각해보면 사용자에게 앱과 상호작용할 수 있는 화면과 환경과 기능을 제공해준다! 

- 아키텍쳐

  MVC모델의 영역은 Model, View, Control이다. View는 화면의 구성을 담당하는 영역이고 우리는 xml파일에서 이를 확인해 봤다. 다음으로 Model은 애플리케이션의 데이터를 저장하는 역할을 담당하고 있다. 마지막으로 Control은 뷰와 데이터를 서로 연결하고 제어하는 역할을 하며 위에서 나온 Activity, 백그라운드에서 실행되는 컴포넌트인 Service와 배터리 부족 등을 수신하거나 반응하는 브로드캐스트 리시버가 있다고한다.