package com.htk.assginment2.data

import com.htk.assginment2.presentation.model.FollowingUserInfo

class LocalFollowingDataSource : FollowingDataSource {
    override fun fetchFollowData(): List<FollowingUserInfo> {
        return listOf<FollowingUserInfo>(
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
    }
}