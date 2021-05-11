package com.istech.appentusmachinetest.Network

import com.istech.appentusmachinetest.dataPojo.Images
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    companion object{
        const val  BASE_URL="https://picsum.photos/";
        const val  IMAGE_BASE_URL="https://picsum.photos/id/";
    }
    @GET("v2/list")
    suspend fun getAllImages(
        @Query("page")page:Int,
        @Query("limit")limit:Int
    ) : List<Images>
}