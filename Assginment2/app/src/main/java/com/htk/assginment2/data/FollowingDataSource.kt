package com.htk.assginment2.data

import com.htk.assginment2.presentation.model.FollowingUserInfo

interface FollowingDataSource {
    fun fetchFollowData() : List<FollowingUserInfo>
}