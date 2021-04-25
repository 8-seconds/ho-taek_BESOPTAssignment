package com.htk.assginment2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htk.assginment2.FollowingAdapter
import com.htk.assginment2.data.FollowingUserInfo
import com.htk.assginment2.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {
    private lateinit var followingAdapter: FollowingAdapter
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

        followingAdapter = FollowingAdapter()

        binding.userList.adapter = followingAdapter

        followingAdapter.userList.addAll(
            listOf<FollowingUserInfo>(
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 4차때 넣어봅시다!",
                    userName = "jinsu4755"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 4차때 넣어봅시다!",
                    userName = "jinsu4755"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 4차때 넣어봅시다!",
                    userName = "jinsu4755"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 4차때 넣어봅시다!",
                    userName = "jinsu4755"
                )

            )
        )
        followingAdapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}