package com.mandeep.paging_with_retrofit_and_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mandeep.paging_with_retrofit_and_viewpager2.MVVM.MyViewModel
import com.mandeep.paging_with_retrofit_and_viewpager2.databinding.ActivityMainBinding
import com.mandeep.paging_with_retrofit_and_viewpager2.paging.MyPagingDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val myViewModel:MyViewModel  by viewModels()
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val myPagingDataAdapter = MyPagingDataAdapter(context = this)

        lifecycleScope.launch {
            myViewModel.listData.collect {
                myPagingDataAdapter.submitData(it)
            }
        }

        val gridLayoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        binding.recyclerVieww.layoutManager = gridLayoutManager
        binding.recyclerVieww.adapter = myPagingDataAdapter

        binding.recyclerVieww.setHasFixedSize(true)

    }
}