package com.noname.androidtask_osamaomar.data.remote

import com.noname.androidtask_osamaomar.models.PostsResponses
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    suspend fun loadMovies(
        @Query("pageSize") pageSize: Int = 10,
        @Query("page") page: Int = 0,
        @Query("apiKey") apiKey: String = "2904712b928a4f4e859a4a4e912b61c3",
        @Query("from") from: String = "2023-01-16",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("q") query: String = "tesla",
    ): PostsResponses?
}
