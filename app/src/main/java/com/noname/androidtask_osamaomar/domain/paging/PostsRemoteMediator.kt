package com.noname.androidtask_osamaomar.domain.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.noname.androidtask_osamaomar.data.local.room.LocalPost
import com.noname.androidtask_osamaomar.data.local.room.PostsDatabase
import com.noname.androidtask_osamaomar.domain.Repository
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PostsRemoteMediator(
    private val postsApiService: Repository,
    private val postsDatabase: PostsDatabase,
) : RemoteMediator<Int, LocalPost>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalPost>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = postsApiService.loadArticles(page)

            val posts = apiResponse?.articles
            val endOfPaginationReached = posts?.isEmpty() ?: false

            postsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postsDatabase.getPostsDao()
                    postsDatabase.getPostsDao().clearAllMovies()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = posts?.map {
                    LocalPost(
                        title = it.title,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        currentPage = page,
                        publishedAt = it.publishedAt,
                        postImage = it.urlToImage
                    )
                }

                postsDatabase.getPostsDao().insertAll(remoteKeys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }


    private fun getRemoteKeyForFirstItem(state: PagingState<Int, LocalPost>): LocalPost? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { post ->
            postsDatabase.getPostsDao().getSelectedPost(post.title)
        }
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, LocalPost>): LocalPost? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { post ->
            postsDatabase.getPostsDao().getSelectedPost(post.title)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, LocalPost>): LocalPost? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.title?.let { id ->
                postsDatabase.getPostsDao().getSelectedPost(id)
            }
        }
    }
}