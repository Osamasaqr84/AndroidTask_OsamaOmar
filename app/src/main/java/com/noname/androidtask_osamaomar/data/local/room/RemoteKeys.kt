package com.noname.androidtask_osamaomar.data.local.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class LocalPost(
    @PrimaryKey(autoGenerate = true)
    val id :Int?=null,
    val title:String,
    val publishedAt:String,
    val postImage:String?,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
):Parcelable