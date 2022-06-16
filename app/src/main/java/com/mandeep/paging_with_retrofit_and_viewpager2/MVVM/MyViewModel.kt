package com.mandeep.paging_with_retrofit_and_viewpager2.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mandeep.paging_with_retrofit_and_viewpager2.paging.MYPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val mainRepositry: MainRepositry) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 20)){
        MYPagingSource(mainRepositry)
    }.flow.cachedIn(viewModelScope)
}