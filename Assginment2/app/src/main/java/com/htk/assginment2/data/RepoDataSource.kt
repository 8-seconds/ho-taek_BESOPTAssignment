package com.htk.assginment2.data

import com.htk.assginment2.presentation.model.RepositoryInfo

interface RepoDataSource {
    fun fetchRepoData(): List<RepositoryInfo>
}