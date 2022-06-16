package com.mandeep.paging_with_retrofit_and_viewpager2.retrofit

import android.content.ClipData
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Items
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    suspend fun getData(@Query("key") key:String, @Query("page") page:Int):Items

}