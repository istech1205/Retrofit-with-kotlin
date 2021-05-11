package com.istech.appentusmachinetest.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.istech.appentusmachinetest.Network.ApiService
import com.istech.appentusmachinetest.dataPojo.Images
import retrofit2.HttpException
import java.io.IOException

class ImagesPagingSource constructor(private val apiService: ApiService) :
    PagingSource<Int, Images>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Images> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllImages(page, params.loadSize)
           LoadResult.Page(
               response,
               prevKey = if (page==1) null else page-1,
               nextKey = if (response.isEmpty()) null else page+1

           )
        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Images>): Int? {
        return null
    }
}