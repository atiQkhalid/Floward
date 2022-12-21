package com.floward.assignment.modal.posts

data class PostsResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)