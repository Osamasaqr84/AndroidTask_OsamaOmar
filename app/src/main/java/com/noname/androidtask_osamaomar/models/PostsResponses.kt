package com.noname.androidtask_osamaomar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PostsResponses (val articles : List<Post>?)

@Parcelize
data class Post(val title:String, val source:Source,val publishedAt:String,val urlToImage:String):Parcelable

@Parcelize
data class Source(val id:String,val name:String):Parcelable