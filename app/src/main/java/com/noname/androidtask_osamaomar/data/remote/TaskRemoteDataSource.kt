package com.noname.androidtask_osamaomar.data.remote

import com.noname.androidtask_osamaomar.models.Post
import com.noname.androidtask_osamaomar.models.PostsResponses
import javax.inject.Inject

class TaskRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun loadArticles( page: Int): PostsResponses? {
        return   apiService.loadMovies(page = page)
    }

}