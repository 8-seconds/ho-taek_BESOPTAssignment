package com.htk.assginment2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.htk.assginment2.adapters.HomeFragmentAdpater
import com.htk.assginment2.data.LocalRepoDataSource
import com.htk.assginment2.data.RepoDataSource
import com.htk.assginment2.presentation.model.RepositoryInfo

import com.htk.assginment2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private val homeFragmentAdapter by lazy { HomeFragmentAdpater()}
    private lateinit var repoDataSource : RepoDataSource
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

       initHomeView()

    }

    private fun initHomeView(){
        binding.repoList.adapter = homeFragmentAdapter
        homeFragmentAdapter.submitList(fetchData())
    }

    private fun fetchData(): List<RepositoryInfo>{
        repoDataSource = LocalRepoDataSource()
        return repoDataSource.fetchRepoData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}