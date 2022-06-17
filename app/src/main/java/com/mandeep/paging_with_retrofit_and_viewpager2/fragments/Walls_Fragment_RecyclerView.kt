package com.mandeep.paging_with_retrofit_and_viewpager2.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import com.mandeep.paging_with_retrofit_and_viewpager2.MVVM.MyViewModel
import com.mandeep.paging_with_retrofit_and_viewpager2.R
import com.mandeep.paging_with_retrofit_and_viewpager2.databinding.FragmentWallsRecyclerViewBinding
import com.mandeep.paging_with_retrofit_and_viewpager2.paging.MyPagingDataAdapter
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Hit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Walls_Fragment_RecyclerView : Fragment() {

    val myViewModel: MyViewModel by viewModels()
    lateinit var binding:FragmentWallsRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentWallsRecyclerViewBinding.inflate(LayoutInflater.from(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction =  requireActivity().supportFragmentManager.beginTransaction()


        val myPagingDataAdapter = MyPagingDataAdapter(context = requireContext(), activity = requireActivity())


        lifecycleScope.launch {
            myViewModel.listData.collect {
                myPagingDataAdapter.submitData(it)
            }
        }

        val gridLayoutManager = GridLayoutManager(requireContext(),3, GridLayoutManager.VERTICAL,false)
        binding.recyclerVieww.layoutManager = gridLayoutManager
        binding.recyclerVieww.adapter = myPagingDataAdapter
        binding.recyclerVieww.setHasFixedSize(true)

        myPagingDataAdapter.setOnCustomClickListener(object: MyPagingDataAdapter.OnCustomOnClickListener{
            override fun onCustomClick(hit: Hit, imageView: View, itemView: View) {




                val transition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

              //  sharedElementEnterTransition = transition
                sharedElementReturnTransition = transition
                exitTransition = transition

                val bundle = Bundle()
                bundle.putParcelable("HIT_OBJECT",hit)

                val wallpaperFragment = Wallpaper_Fragment()
                wallpaperFragment.sharedElementEnterTransition = transition
               wallpaperFragment.enterTransition = transition
                wallpaperFragment.arguments = bundle
                transaction.replace(R.id.fragmenttContainer,wallpaperFragment)
                transaction.addToBackStack(null)

                transaction.addSharedElement(imageView,hit.id.toString()+"t")
                transaction.commit()



            }
        })
    }


}