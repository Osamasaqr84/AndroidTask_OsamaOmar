package com.noname.androidtask_osamaomar.data.remote

import com.noname.androidtask_osamaomar.domain.Repository
import com.noname.androidtask_osamaomar.models.PostsResponses
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: TaskRemoteDataSource) : Repository {


    override suspend fun loadArticles(page:Int): PostsResponses? {
        return  remoteDataSource.loadArticles(page)
    }



}