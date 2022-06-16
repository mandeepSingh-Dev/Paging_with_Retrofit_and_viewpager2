package com.mandeep.paging_with_retrofit_and_viewpager2.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.mandeep.paging_with_retrofit_and_viewpager2.MVVM.MyViewModel
import com.mandeep.paging_with_retrofit_and_viewpager2.databinding.FragmentWallBinding
import com.mandeep.paging_with_retrofit_and_viewpager2.paging.MyPagingDataAdapter
import com.mandeep.paging_with_retrofit_and_viewpager2.paging.MyPagingDataAdapter_for_ViewPager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Wall_Fragment : Fragment() {

    lateinit var binding:FragmentWallBinding
    val myViewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentWallBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mypagingdataadapterForViewpager = MyPagingDataAdapter_for_ViewPager(context = requireContext())

        lifecycleScope.launch {
            myViewModel.listData.collect {
                mypagingdataadapterForViewpager.submitData(it)
            }
        }

       // val gridLayoutManager = GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false)
        binding.viewPagerr2.adapter = mypagingdataadapterForViewpager
        binding.viewPagerr2.offscreenPageLimit = 3

        TabLayoutMediator(binding.tablayout,binding.viewPagerr2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        }).attach()


    }

}