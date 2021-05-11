package com.istech.appentusmachinetest.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.istech.appentusmachinetest.Network.ApiService
import com.istech.appentusmachinetest.dataPojo.Images
import com.istech.appentusmachinetest.repository.ImagesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel
@Inject
constructor(private val  apiService: ApiService) : ViewModel() {

    fun getAllImages(): Flow<PagingData<Images>> =Pager(
        config = PagingConfig(10,enablePlaceholders = false),
        ){
        ImagesPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)


}