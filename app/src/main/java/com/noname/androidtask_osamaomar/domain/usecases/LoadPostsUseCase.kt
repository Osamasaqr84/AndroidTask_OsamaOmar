package com.noname.androidtask_osamaomar.domain.usecases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.noname.androidtask_osamaomar.data.local.room.LocalPost
import com.noname.androidtask_osamaomar.data.local.room.PostsDatabase
import com.noname.androidtask_osamaomar.domain.Repository
import com.noname.androidtask_osamaomar.domain.paging.PostsRemoteMediator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoadPostsUseCase @Inject constructor(private val repository: Repository,
                                           private val postsDatabase: PostsDatabase,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun invoke(dispatcher : CoroutineDispatcher = Dispatchers.IO): Flow<PagingData<LocalPost>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            initialKey = 1,
            pagingSourceFactory = { postsDatabase.getPostsDao().getPosts() },
            remoteMediator = PostsRemoteMediator(repository,postsDatabase)
        ).flow.flowOn(dispatcher)
    }
}