package com.htk.assginment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.htk.assginment2.presentation.model.FollowingUserInfo
import com.htk.assginment2.databinding.ItemFollowUserBinding

class FollowingAdapter:
    ListAdapter<FollowingUserInfo, FollowingAdapter.FollowingViewHolder>(FollowDiffUtil) {

        class FollowingViewHolder(private val binding : ItemFollowUserBinding) :
                RecyclerView.ViewHolder(binding.root) {
                    fun onBind(followingInfo : FollowingUserInfo){
                        binding.followingUserInfo = followingInfo
                    }
                }

        object FollowDiffUtil : DiffUtil.ItemCallback<FollowingUserInfo>() {
            override fun areItemsTheSame(oldItem: FollowingUserInfo, newItem: FollowingUserInfo
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: FollowingUserInfo, newItem: FollowingUserInfo
            ): Boolean {
                return oldItem == newItem
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowUserBinding.inflate(layoutInflater, parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
