package com.htk.assginment2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htk.assginment2.FollowingAdapter
import com.htk.assginment2.data.FollowingDataSource
import com.htk.assginment2.data.LocalFollowingDataSource
import com.htk.assginment2.presentation.model.FollowingUserInfo
import com.htk.assginment2.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {
    private val followingFragmentAdapter by lazy {FollowingAdapter()}
    private lateinit var followingDataSource : FollowingDataSource
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding?: error("View를 참조하기 위해 binding이 초기화 되지 않았습니다.")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
        binding.tvFollow
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        followingFragmentAdapter.submitList(fetchData())
    }


    private fun initView() {
        binding.userList.adapter = followingFragmentAdapter

    }

    private fun fetchData(): List<FollowingUserInfo>{
        followingDataSource = LocalFollowingDataSource()
        return followingDataSource.fetchFollowData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}