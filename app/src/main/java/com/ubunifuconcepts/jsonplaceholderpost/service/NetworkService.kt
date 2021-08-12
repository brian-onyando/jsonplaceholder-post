package com.ubunifuconcepts.jsonplaceholderpost.model.service

import com.ubunifuconcepts.jsonplaceholderpost.model.Post
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {

    @POST("posts")
    suspend fun publishPost(@Body post: Post): ResponseBody
}