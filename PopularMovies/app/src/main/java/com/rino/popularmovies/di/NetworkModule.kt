package com.rino.popularmovies.di

import com.rino.popularmovies.BuildConfig
import com.rino.popularmovies.remote.MovieDbService
import com.rino.popularmovies.remote.interceptors.HeadersInterceptor
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
            .addInterceptor(HeadersInterceptor())

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
            .baseUrl(BuildConfig.MOVIEDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getMovieDbService(retrofit: Retrofit): MovieDbService =
        retrofit.create(MovieDbService::class.java)

}