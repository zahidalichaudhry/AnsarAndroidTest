package com.zahid.ansarandroidtest.model


import com.google.gson.annotations.SerializedName

data class Result(
    val id: String,
    val subCategories: List<SubCategory>,
    val title: String
)