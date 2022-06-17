package com.mandeep.paging_with_retrofit_and_viewpager2.paging

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.paging_with_retrofit_and_viewpager2.R
import com.mandeep.paging_with_retrofit_and_viewpager2.retrofit.dataclasses.Hit
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class MyPagingDataAdapter(val context:Context,val activity: Activity) : PagingDataAdapter<Hit,MyPagingDataAdapter.MyViewHolder>(diffUtilItemCallBack) {


    lateinit var onCustomOnClickListener:OnCustomOnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.items,parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hit = getItem(position)

        holder.idTextView.text = hit?.id.toString()


      //  Glide.with(context).load(hit?.largeImageURL).into(holder.imageVIew)
        Glide.with(context).load(hit?.largeImageURL).error(R.drawable.ic_launcher_background).centerCrop().transition(DrawableTransitionOptions.withCrossFade()).into(holder.imageVIew)

        holder.itemView.setOnClickListener {
            hit?.let {
                holder.itemView.setTransitionName("anyString" + position)

                holder.imageVIew.transitionName = hit?.id.toString()
                Log.d("gubenge",hit?.id.toString())
                onCustomOnClickListener.onCustomClick(it, holder.imageVIew,holder.itemView)
            }

        }

    }

    object diffUtilItemCallBack : DiffUtil.ItemCallback<Hit>(){
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
              return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
           return oldItem == newItem
        }
    }


    class MyViewHolder( itemView: View):RecyclerView.ViewHolder(itemView){
        val idTextView: TextView = itemView.findViewById(R.id.wallpaperIdsTextView)
        val imageVIew: ImageView = itemView.findViewById(R.id.wallpaperImageVIew)

    }


    interface OnCustomOnClickListener{
        fun onCustomClick(hit:Hit,imageView:View,itemView: View)
    }

    fun setOnCustomClickListener(onCustomOnClickListener: OnCustomOnClickListener){
        this.onCustomOnClickListener = onCustomOnClickListener
    }


}