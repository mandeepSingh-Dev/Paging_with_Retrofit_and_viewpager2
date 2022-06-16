package com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses

data class Items(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)