package com.istech.appentusmachinetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.drawable.CrossfadeDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.istech.appentusmachinetest.Network.ApiService
import com.istech.appentusmachinetest.dataPojo.Images
import com.istech.appentusmachinetest.databinding.ImagesLayoutBinding
import javax.inject.Inject
@GlideModule
class ImagesAdapter

@Inject
constructor() : PagingDataAdapter<Images,ImagesAdapter.ImageViewHolder>(Diff) {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val images=getItem(position)
        if (images!=null){
            holder.bind(images)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
      return ImageViewHolder(ImagesLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    class ImageViewHolder(private val binding: ImagesLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(images: Images){
            binding.apply {
                Glide.with(binding.root.context)
                    .load(ApiService.IMAGE_BASE_URL+images.id+"/150/150")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivImage)
            }
        }
    }
    object  Diff: DiffUtil.ItemCallback<Images>(){
        override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean =
            oldItem.download_url==newItem.download_url

        override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean=
            oldItem==newItem

    }
}