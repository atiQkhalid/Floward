package com.floward.assignment.network

import com.floward.assignment.modal.posts.PostsResponseItem
import com.floward.assignment.modal.users.UsersResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The APIInterface.kt
 */
interface ApiInterface {
    @GET("users")
    fun getUsers(
    ): Call<List<UsersResponseItem>>

    @GET("posts")
    fun getPosts(
        @Query("userId") userId: Int?
    ): Call<List<PostsResponseItem>>

    @GET("posts")
    fun getPosts(
    ): Call<List<PostsResponseItem>>
}