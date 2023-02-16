package com.noname.androidtask_osamaomar.presentation.posts

import com.noname.androidtask_osamaomar.data.local.room.LocalPost

interface OnItemClickListener {
    fun onItemClick(localPost: LocalPost?)
}