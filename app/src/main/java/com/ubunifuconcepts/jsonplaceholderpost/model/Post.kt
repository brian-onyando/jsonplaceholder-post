package com.ubunifuconcepts.jsonplaceholderpost.model

import com.google.gson.annotations.SerializedName

data class Post(
    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("id")
    val id: Int = 0
)