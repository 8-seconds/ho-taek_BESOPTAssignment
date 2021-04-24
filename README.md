### Level1: 안린이 탈출하기 (HomeActivity 추가하기!)

- item_home.xml - recyclerview item 만들기.(ellipsize 사용해서 ...만들기)

  ```
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:padding="20dp">
  
      <TextView
          android:id="@+id/tv_rp_name"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:text="@string/rp_name"
          android:textSize="20sp"
          android:textStyle="bold"
          android:maxLength="1"
          android:ellipsize="end"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  
      <TextView
          android:id="@+id/tv_rp_explain"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_marginTop="10dp"
          android:hint="@string/rp_explain"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/tv_rp_name" />
  
      <TextView
          android:id="@+id/tv_language"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_marginTop="10dp"
          android:hint="@string/language"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/tv_rp_explain" />
  
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```



- homeFragment dataclass 만들기

  ```
  data class RepositoryInfo(
      val repoName : String,
      val repoExplain : String,
      val language : String?
  )
  ```

  

- ViewHolder 만들기

  ```
   class HomeFragmentViewHolder(
          private val binding : ItemHomeBinding
      ) : RecyclerView.ViewHolder(binding.root){
          fun onBind(repositoryInfo: RepositoryInfo){
              binding.tvRpName.text = repositoryInfo.repoName
              binding.tvRpExplain.text = repositoryInfo.repoExplain
              binding.tvLanguage.text = repositoryInfo.language
          }
      }
  ```

  

- HomeFragmentAdapter

  ```
  class HomeFragmentAdpater: RecyclerView.Adapter<HomeFragmentAdpater.HomeFragmentViewHolder>() {
      val repoList = mutableListOf<RepositoryInfo>()
  
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
          val binding = ItemHomeBinding.inflate(
              LayoutInflater.from(parent.context),
              parent,
              false)
  
          return HomeFragmentViewHolder(binding)
      }
  
      override fun onBindViewHolder(
          holder: HomeFragmentAdpater.HomeFragmentViewHolder,
          position: Int
      ) {
      holder.onBind(repoList[position])
      }
  
      override fun getItemCount(): Int {
          return repoList.size
      }
  ```

  

- RecyclerView 배치(fragment_home.xml)

  ```
  <?xml version="1.0" encoding="utf-8"?>
  <androidx.constraintlayout.widget.ConstraintLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".presentation.HomeFragment">
  
      <androidx.recyclerview.widget.RecyclerView
          android:id="@+id/repo_list"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
          app:layout_constraintTop_toTopOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          tools:listitem="@layout/item_home"/>
  
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```

  

- activity_home에 Fragment 부착하기

  ```
  <androidx.constraintlayout.widget.ConstraintLayout
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              app:layout_constraintTop_toBottomOf="@+id/cl_home">
  
              <androidx.fragment.app.FragmentContainerView
                  android:id="@+id/home_fragment"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent" />
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```

  

- HomeActivity에 fragment 연결하기

  ```
  HomeActivity : AppCompatActivity() {
      private lateinit var binding : ActivityHomeBinding
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityHomeBinding.inflate(layoutInflater)
          setContentView(binding.root)
  
          val homeFragment = HomeFragment()
  
          val transaction = supportFragmentManager.beginTransaction()
          transaction.add(R.id.home_fragment, homeFragment)
          transaction.commit()
  ```

  

  