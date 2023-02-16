package com.noname.androidtask_osamaomar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.noname.androidtask_osamaomar.data.local.room.LocalPost
import com.noname.androidtask_osamaomar.domain.usecases.LoadPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val loadPostsUseCase: LoadPostsUseCase
) : ViewModel() {

    val articlesFlow = MutableStateFlow<PagingData<LocalPost>>(PagingData.empty())

    fun loadArticlesDate() {
        loadPostsUseCase.invoke().onEach {
            articlesFlow.emit(it)
        }.cachedIn(viewModelScope).launchIn(viewModelScope)
    }

}