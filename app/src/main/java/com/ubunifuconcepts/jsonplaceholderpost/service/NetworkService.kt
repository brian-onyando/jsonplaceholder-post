package com.ubunifuconcepts.jsonplaceholderpost.service

import com.ubunifuconcepts.jsonplaceholderpost.model.Post
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {

    @POST("posts")
    suspend fun publishPost(@Body post: Post): Post
}