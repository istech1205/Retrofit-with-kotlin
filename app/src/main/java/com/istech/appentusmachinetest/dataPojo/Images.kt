package com.istech.appentusmachinetest.dataPojo

import com.squareup.moshi.Json

data class Images(
    @Json (name = "id")
    val id:String,
    @Json (name = "download_url")
    val download_url:String
)
