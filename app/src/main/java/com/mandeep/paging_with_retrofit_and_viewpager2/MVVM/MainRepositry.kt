package com.mandeep.paging_with_retrofit_and_viewpager2.MVVM

import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.ApiService
import javax.inject.Inject

class MainRepositry @Inject constructor(val apiService: ApiService)
{

   suspend fun getData(key:String,page:Int) = apiService.getData(key = key, page = page)

}