package com.ubunifuconcepts.jsonplaceholderpost.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ubunifuconcepts.jsonplaceholderpost.R
import com.ubunifuconcepts.jsonplaceholderpost.model.Post
import com.ubunifuconcepts.jsonplaceholderpost.viewmodel.PostsViewModel

class PostsActivity : AppCompatActivity() {
    private val userId = 100
    lateinit var postAdapter: PostAdapter
    lateinit var postsRecyclerView: RecyclerView
    lateinit var postsViewModel: PostsViewModel
    lateinit var etTitle: EditText
    lateinit var etBody: EditText
    lateinit var btnPublish: Button
    private val loadingVisual: AlertDialog by lazy {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.publishing))
            .setCancelable(false)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        initialize()
    }

    private fun initialize() {
        initializeViewModel()
        initializeFormWidgets()
        setupRecyclerView()
        observePosts()
        observeErrors()
        registerRefreshButtonListener()
    }

    private fun initializeFormWidgets() {
        etTitle = findViewById(R.id.etTitle)
        etBody = findViewById(R.id.etBody)
        btnPublish = findViewById(R.id.btnPublishPost)
    }

    private fun initializeViewModel() {
        postsViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    private fun setupRecyclerView() {
        postsRecyclerView = findViewById(R.id.rvPosts)
        postAdapter = PostAdapter()
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postAdapter
    }

    private fun observePosts() {
        postsViewModel.postsLiveData
            .observe(this, { posts ->
                updatePostsList(posts)
                hideLoadingSpinner()
            })
    }

    private fun observeErrors() {
        postsViewModel.errorsLiveData
            .observe(this, { error ->
                hideLoadingSpinner()
                Snackbar.make(
                    postsRecyclerView,
                    error,
                    Snackbar.LENGTH_LONG
                ).also { snackBar ->
                    snackBar.show()
                }
            })
    }

    private fun registerRefreshButtonListener() {
        btnPublish.setOnClickListener {
            if (formDataValidated()) {
                publishFormData()
            }
        }
    }

    private fun formDataValidated(): Boolean {
        var validFormData = false
        when {
            getTitleValueFromForm().isBlank() -> {
                etTitle.error = "Please enter a title"
            }
            getBodyTextFromForm().isBlank() -> {
                etTitle.error = "Please enter some post content"
            }
            else -> {
                validFormData = true
            }
        }
        return validFormData
    }

    private fun publishFormData() {
        showLoadingSpinner()
        postsViewModel.publishPost(getPostFromFormData())
    }

    private fun getPostFromFormData(): Post {
        return Post(
            userId = getUserId(),
            title = getTitleValueFromForm(),
            body = getBodyTextFromForm()
        )
    }

    private fun getBodyTextFromForm() = etBody.text.toString()

    private fun getTitleValueFromForm() = etTitle.text.toString()

    private fun getUserId(): Int {
        return this.userId
    }

    private fun updatePostsList(posts: List<Post>) {
        postAdapter.setData(posts)
    }

    private fun showLoadingSpinner() {
        loadingVisual.show()
    }

    private fun hideLoadingSpinner() {
        loadingVisual.hide()
    }
}