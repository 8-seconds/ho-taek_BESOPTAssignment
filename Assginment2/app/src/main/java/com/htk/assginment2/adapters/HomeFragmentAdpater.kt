package com.htk.assginment2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.htk.assginment2.data.RepositoryInfo
import com.htk.assginment2.databinding.ItemHomeBinding

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

    class HomeFragmentViewHolder(
        private val binding : ItemHomeBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(repositoryInfo: RepositoryInfo){
            binding.tvRpName.text = repositoryInfo.repoName
            binding.tvRpExplain.text = repositoryInfo.repoExplain
            binding.tvLanguage.text = repositoryInfo.language
        }
    }
}