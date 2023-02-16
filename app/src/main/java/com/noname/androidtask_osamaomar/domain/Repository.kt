package com.noname.androidtask_osamaomar.domain

import com.noname.androidtask_osamaomar.models.PostsResponses

interface Repository  {

  suspend  fun loadArticles(page:Int ) : PostsResponses?

}