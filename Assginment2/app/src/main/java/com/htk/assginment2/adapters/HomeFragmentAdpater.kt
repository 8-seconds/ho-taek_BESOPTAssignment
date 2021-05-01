package com.htk.assginment2.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.htk.assginment2.presentation.model.RepositoryInfo
import com.htk.assginment2.databinding.ItemHomeBinding

class HomeFragmentAdpater :
    ListAdapter<RepositoryInfo, HomeFragmentAdpater.HomeFragmentViewHolder>(RepoDiffUtil) {

    class HomeFragmentViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onRepoBind(repoInfo: RepositoryInfo) {
            binding.repositoryInfo = repoInfo
        }
    }

    //notifyDataSetchanged() 대신 사용
    object RepoDiffUtil : DiffUtil.ItemCallback<RepositoryInfo>(){
        override fun areItemsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentAdpater.HomeFragmentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHomeBinding.inflate(layoutInflater, parent, false)
            return HomeFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentAdpater.HomeFragmentViewHolder, position: Int) {
        holder.onRepoBind(getItem(position))
    }
}

