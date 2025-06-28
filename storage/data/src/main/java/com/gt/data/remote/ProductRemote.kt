package com.gt.data.remote

import com.google.gson.annotations.SerializedName

data class ProductRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val data: ProductDataRemote?
)
