package com.htk.assginment2.data

import com.htk.assginment2.presentation.model.RepositoryInfo

class LocalRepoDataSource : RepoDataSource {
    override fun fetchRepoData(): List<RepositoryInfo> {
        return listOf<RepositoryInfo>(
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
    }
}