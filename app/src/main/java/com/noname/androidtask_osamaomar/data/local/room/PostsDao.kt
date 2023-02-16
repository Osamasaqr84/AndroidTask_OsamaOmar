package com.noname.androidtask_osamaomar.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<LocalPost>?)

    @Query("Select * From posts Order By currentPage")
    fun getPosts(): PagingSource<Int, LocalPost>


    @Query("Select * From posts WHERE title = :title")
    fun getSelectedPost(title:String): LocalPost?


    @Query("Delete From posts")
    suspend fun clearAllMovies()
}