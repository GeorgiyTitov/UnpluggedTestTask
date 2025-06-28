package com.gt.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("objects")
    suspend fun getProducts(): List<ProductRemote>

    @GET("objects/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): ProductRemote
}
