package com.ubunifuconcepts.jsonplaceholderpost.repository

import android.util.Log
import com.ubunifuconcepts.common.RestClient
import com.ubunifuconcepts.jsonplaceholderpost.model.Post
import com.ubunifuconcepts.jsonplaceholderpost.service.NetworkService

object PostsRepository {
    suspend fun publishPost(post: Post): Post? {
        return try {
            RestClient.service(NetworkService::class.java).publishPost(post)
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error fetching posts: ${e.message}")
            null
        }
    }
}