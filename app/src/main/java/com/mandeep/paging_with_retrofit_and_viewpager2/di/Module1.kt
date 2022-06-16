package com.mandeep.paging_with_retrofit_and_viewpager2.di

import android.app.Application
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class Module1 {

    val BASE_URL = "https://pixabay.com"

    @Provides
    fun provideService() = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
}