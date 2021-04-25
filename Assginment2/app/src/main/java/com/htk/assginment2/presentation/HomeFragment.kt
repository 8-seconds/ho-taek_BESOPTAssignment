package com.htk.assginment2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.htk.assginment2.adapters.HomeFragmentAdpater
import com.htk.assginment2.data.RepositoryInfo

import com.htk.assginment2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var homeFragmentAdpater: HomeFragmentAdpater
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화 되지 않았습니다.")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeFragmentAdpater = HomeFragmentAdpater()
        binding.repoList.adapter = homeFragmentAdpater

        homeFragmentAdpater.repoList.addAll(
            listOf(
                RepositoryInfo(
                    repoName = "팀플",
                    repoExplain = "팀프로젝트",
                    language = "코틀린"
                ),
                RepositoryInfo(
                    repoName = "솝트_안드",
                    repoExplain = "세미나",
                    language = "코틀린"
                ),
                RepositoryInfo(
                    repoName = "파이썬 스터디",
                    repoExplain = "코테 뿌셔!!",
                    language = "파이썬"
                )

            )
        )
        homeFragmentAdpater.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}