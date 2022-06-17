package com.mandeep.paging_with_retrofit_and_viewpager2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.mandeep.paging_with_retrofit_and_viewpager2.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    lateinit var binding :ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        val intent = Intent(this,MainActivity::class.java)

        binding.cardView1.setOnClickListener {
            intent.putExtra("FRAGMENT_NAME","Wallpapers_Fragment_With_RecyclerView")
            startActivity(intent)
        }

        binding.cardView2.setOnClickListener {
            intent.putExtra("FRAGMENT_NAME","Wallpapers_Fragment_With_ViewPager")
            startActivity(intent)
        }



    }
}