package com.mandeep.paging_with_retrofit_and_viewpager2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mandeep.paging_with_retrofit_and_viewpager2.R
import com.mandeep.paging_with_retrofit_and_viewpager2.databinding.FragmentWallpaperBinding
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Hit


class Wallpaper_Fragment : Fragment() {

    lateinit var binding:FragmentWallpaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentWallpaperBinding.inflate(LayoutInflater.from(requireContext()))

        val hit = arguments?.getParcelable<Hit>("HIT_OBJECT")

        Log.d("gubenge",hit?.id.toString()+" From wallpaper fragment")

        binding.imageviewWall.transitionName = hit?.id.toString()+"t"
        Glide.with(activity!!).load(hit?.largeImageURL).into(binding.imageviewWall)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }

}