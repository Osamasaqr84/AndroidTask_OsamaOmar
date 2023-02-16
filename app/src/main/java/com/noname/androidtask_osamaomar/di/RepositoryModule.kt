package com.noname.androidtask_osamaomar.di

import com.noname.androidtask_osamaomar.data.remote.ApiService
import com.noname.androidtask_osamaomar.data.remote.RepositoryImp
import com.noname.androidtask_osamaomar.data.remote.TaskRemoteDataSource
import com.noname.androidtask_osamaomar.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun providesDataSource( apiService: ApiService) : TaskRemoteDataSource = TaskRemoteDataSource(apiService)


}