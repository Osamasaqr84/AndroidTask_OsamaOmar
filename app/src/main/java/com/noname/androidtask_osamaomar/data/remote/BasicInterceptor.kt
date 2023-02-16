package com.noname.androidtask_osamaomar.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BasicInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("X-RapidAPI-Host", "finance-social-sentiment-for-twitter-and-stocktwits.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", "1ad4380175msh50ebf7bfe3a791ap17af53jsnaeddaf4b7dd9")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}
