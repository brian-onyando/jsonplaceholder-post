package com.ubunifuconcepts.jsonplaceholderpost.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubunifuconcepts.jsonplaceholderpost.R
import com.ubunifuconcepts.jsonplaceholderpost.model.Post
import com.ubunifuconcepts.jsonplaceholderpost.view.viewholder.PostViewHolder


class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private var posts = listOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.layout_post_item,
                parent,
                false
            )
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setData(posts[position])
    }

    fun setData(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }
}