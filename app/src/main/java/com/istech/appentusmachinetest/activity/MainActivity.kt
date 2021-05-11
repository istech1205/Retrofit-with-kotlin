package com.istech.appentusmachinetest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.istech.appentusmachinetest.R
import com.istech.appentusmachinetest.ViewModel.ImagesViewModel
import com.istech.appentusmachinetest.adapter.ImagesAdapter
import com.istech.appentusmachinetest.adapter.LoadingStateAdapter
import com.istech.appentusmachinetest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imagesViewModel: ImagesViewModel by viewModels()
    @Inject
    lateinit var imagesAdapter : ImagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView();
        loadData();
    }

    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            imagesViewModel.getAllImages().collectLatest {response->
                binding.apply {
                    rvImageList.isVisible=true
                    progressBar.isVisible=false
                }
                imagesAdapter.submitData(response)
            }
        }
    }

    private fun initRecycleView() {
       binding.apply {
           rvImageList.apply {
               setHasFixedSize(true)
               layoutManager=GridLayoutManager(this@MainActivity,2)
               adapter=imagesAdapter.withLoadStateHeaderAndFooter(
                   header = LoadingStateAdapter{imagesAdapter.retry()},
                   footer = LoadingStateAdapter{imagesAdapter.retry()}
               )
           }
       }
    }
}