package com.mandeep.paging_with_retrofit_and_viewpager2.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mandeep.paging_with_retrofit_and_viewpager2.MVVM.MainRepositry
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.API_KEY
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Hit
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Items
import java.lang.Exception

class MYPagingSource(val mainRepositry: MainRepositry):PagingSource<Int,Hit>() {

 //   val key = "17284571-9dc44bcf97e2f82106c65a55e"

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {

        try {
            val currentKey = params.key ?: 1

            val hitList = mutableListOf<Hit>()
            val items = mainRepositry.getData(API_KEY.APIKEY, currentKey)

           items.hits.let {
               hitList.addAll(it)
           }

            val prevkey = if (currentKey == 1) null else currentKey - 1
            val nextKey = if (hitList.isEmpty()) null else currentKey + 1

            Log.d("90rj4f",currentKey.toString() + "  currentKey")
            Log.d("90rj4f",prevkey.toString() + "  prevkey")
            Log.d("90rj4f",nextKey.toString() + "  nextKey")

            val page = LoadResult.Page(data = hitList, prevKey = prevkey, nextKey = nextKey)
            return page
        }catch (e:Exception)
        {
            return LoadResult.Error(e)
        }
    }
}