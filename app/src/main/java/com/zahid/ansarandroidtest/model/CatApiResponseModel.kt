package com.zahid.ansarandroidtest.model


import com.google.gson.annotations.SerializedName

data class CatApiResponseModel(
    val errorMessage: Any,
    val paging: Any,
    val result: ArrayList<Result>,
    val serverTime: String
)