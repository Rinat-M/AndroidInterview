package com.rino.redditinfinitylistapp.di

import com.rino.redditinfinitylistapp.BuildConfig
import com.rino.redditinfinitylistapp.remote.RedditService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.MINUTES)

        getLoggerInterceptor()?.let {
            httpClient.addInterceptor(it)
        }

        return httpClient.build()
    }

    private fun getLoggerInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            null
        }

    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.REDDIT_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRedditService(retrofit: Retrofit): RedditService =
        retrofit.create(RedditService::class.java)

}