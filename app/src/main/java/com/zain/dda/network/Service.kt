package com.zain.dda.network

import com.zain.dda.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("7.json")
    suspend fun getArticles(
//        @Query("page") page: Int,
        @Query("api-key") apiKey: String
    ): Response

}