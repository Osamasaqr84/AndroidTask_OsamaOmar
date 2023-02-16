package com.noname.androidtask_osamaomar.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalPost::class], version = 4)
abstract class PostsDatabase: RoomDatabase() {
    abstract fun getPostsDao(): PostsDao
}