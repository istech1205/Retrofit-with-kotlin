package com.istech.appentusmachinetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.istech.appentusmachinetest.databinding.ErrorLayoutBinding

class LoadingStateAdapter
constructor(private  val retry : ()->Unit)
    : LoadStateAdapter<LoadingStateAdapter.LoadingViewHolder>(){

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
       holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
      return LoadingViewHolder(ErrorLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false),retry)
    }

    class LoadingViewHolder (private val binding: ErrorLayoutBinding,retry: () -> Unit): RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.btRetryData.setOnClickListener {
                retry()
            }
        }
        fun bind(loadState: LoadState)
        {
            binding.apply {
                progressBar.isVisible=loadState is LoadState.Loading
                btRetryData.isVisible=loadState !is LoadState.Loading
            }
        }
    }
}