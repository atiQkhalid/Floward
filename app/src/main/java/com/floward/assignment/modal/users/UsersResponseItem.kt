package com.floward.assignment.modal.users

data class UsersResponseItem(
    val albumId: Int,
    val name: String,
    val thumbnailUrl: String,
    val url: String,
    val userId: Int,
    var postCount: Int
)