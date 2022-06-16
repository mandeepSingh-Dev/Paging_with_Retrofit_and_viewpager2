package com.mandeep.paging_with_retrofit_and_viewpager2.paging

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.paging_with_retrofit_and_viewpager2.R
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Hit
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Items
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition


class MyPagingDataAdapter_for_ViewPager(val context:Context) : PagingDataAdapter<Hit,MyPagingDataAdapter_for_ViewPager.MyViewHolder>(diffUtilItemCallBack) {

    class MyViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView){
        val idTextView: TextView = itemView.findViewById(R.id.wallpaperIdsTextView)
        val imageVIew: ImageView = itemView.findViewById(R.id.wallpaperImageVIew)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items_viewpager,parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hit = getItem(position)

        holder.idTextView.text = hit?.id.toString()


        //  Glide.with(context).load(hit?.largeImageURL).into(holder.imageVIew)
        Glide.with(context).load(hit?.largeImageURL).error(R.drawable.ic_launcher_background).centerCrop().transition(DrawableTransitionOptions.withCrossFade()).into(holder.imageVIew)

    }

    object diffUtilItemCallBack : DiffUtil.ItemCallback<Hit>(){
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }


}