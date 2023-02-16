package com.noname.androidtask_osamaomar.di

import android.content.Context
import androidx.room.Room
import com.noname.androidtask_osamaomar.data.local.room.PostsDao
import com.noname.androidtask_osamaomar.data.local.room.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {


    @Singleton
    @Provides
    fun providePostsDatabase(@ApplicationContext context: Context): PostsDatabase =
        Room.databaseBuilder(context, PostsDatabase::class.java, "posts_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePostsDao(postsDatabase: PostsDatabase): PostsDao = postsDatabase.getPostsDao()


}