package com.ubunifuconcepts.jsonplaceholderpost.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubunifuconcepts.jsonplaceholderpost.model.Post
import com.ubunifuconcepts.jsonplaceholderpost.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsViewModel : ViewModel() {
    private val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    private val errorsLiveData: MutableLiveData<String> = MutableLiveData()
    private val networkErrorString = "Error publishing post!"

    fun publishPost(post: Post) {
        viewModelScope.launch(IO) {
            Log.d(javaClass.simpleName, "Fetching posts data")
            val createdPost = PostsRepository.publishPost(post)
            withContext(Dispatchers.Main) {
                if (createdPost != null) {
                    val posts = postsLiveData.value?.toMutableList() ?: mutableListOf()
                    posts.add(createdPost)
                    postsLiveData.value = posts
                } else {
                    errorsLiveData.value = networkErrorString
                }
            }
        }
    }
}