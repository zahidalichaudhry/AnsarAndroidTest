package com.zahid.ansarandroidtest.api

import com.zahid.ansarandroidtest.model.CatApiResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface ApiService {

    @GET("/api/Category")
    fun categories(): Call<CatApiResponseModel>
}