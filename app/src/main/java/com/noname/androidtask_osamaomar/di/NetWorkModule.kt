package com.noname.androidtask_osamaomar.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.noname.androidtask_osamaomar.BuildConfig
import com.noname.androidtask_osamaomar.data.remote.BasicInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: BasicInterceptor): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).build()


    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    @Provides
    fun provideGson() = GsonBuilder().setLenient().create()


    @Provides
    fun getBaseInterceptor(): BasicInterceptor = BasicInterceptor()


}