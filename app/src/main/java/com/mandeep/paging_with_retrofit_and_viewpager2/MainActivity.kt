package com.mandeep.paging_with_retrofit_and_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import com.mandeep.paging_with_retrofit_and_viewpager2.databinding.ActivityMainBinding
import com.mandeep.paging_with_retrofit_and_viewpager2.fragments.Wallpaper_Fragment
import com.mandeep.paging_with_retrofit_and_viewpager2.fragments.Walls_Fragment_RecyclerView
import com.mandeep.paging_with_retrofit_and_viewpager2.fragments.Walls_Fragment_ViewPager
import com.mandeep.paging_with_retrofit_and_viewpager2.paging.MyPagingDataAdapter
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Hit
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   // val myViewModel:MyViewModel  by viewModels()
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val myPagingDataAdapter = MyPagingDataAdapter(context = this, activity = this)

/*        lifecycleScope.launch {
            myViewModel.listData.collect {
                myPagingDataAdapter.submitData(it)
            }
        }

        val gridLayoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        binding.recyclerVieww.layoutManager = gridLayoutManager
        binding.recyclerVieww.adapter = myPagingDataAdapter
        binding.recyclerVieww.setHasFixedSize(true)

        myPagingDataAdapter.setOnCustomClickListener(object:MyPagingDataAdapter.OnCustomOnClickListener{
            override fun onCustomClick(hit: Hit,itemView: View) {
                val wallpaperFragment = Wallpaper_Fragment()
                wallpaperFragment.sharedElementEnterTransition = MaterialContainerTransform()

             val transaction =  supportFragmentManager.beginTransaction()
                transaction.addSharedElement(itemView,"shared_element_container")
                transaction.replace()

            }
        })*/

        val fragmentNameString =  intent.getStringExtra("FRAGMENT_NAME")
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when(fragmentNameString){
            "Wallpapers_Fragment_With_RecyclerView" ->
            {
                    val fragmentWalls_Fragment_RecyclerView = Walls_Fragment_RecyclerView()
                fragmentTransaction.replace(R.id.fragmenttContainer,fragmentWalls_Fragment_RecyclerView,)
                fragmentTransaction.commit()
            }
            else ->
            {
                val fragmentWalls_Fragment_ViewPager = Walls_Fragment_ViewPager()
                fragmentTransaction.replace(R.id.fragmenttContainer,fragmentWalls_Fragment_ViewPager,)
                fragmentTransaction.commit()
            }

        }
    }

}