package com.ubunifuconcepts.jsonplaceholderpost.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ubunifuconcepts.jsonplaceholderpost.R
import com.ubunifuconcepts.jsonplaceholderpost.model.Post

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val postTitle: AppCompatTextView = itemView.findViewById(R.id.tvPostTitle)
    private val postBody: AppCompatTextView = itemView.findViewById(R.id.tvPostBody)

    fun setData(post: Post) {
        postTitle.text = post.title
        postBody.text = post.body
    }
}