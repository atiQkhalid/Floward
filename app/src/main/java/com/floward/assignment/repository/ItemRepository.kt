package com.floward.assignment.repository

import com.floward.assignment.App
import com.floward.assignment.R
import com.floward.assignment.modal.posts.PostsResponseItem
import com.floward.assignment.modal.users.UsersResponseItem
import com.floward.assignment.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository {

    private val apiInterface = RetrofitClient.getInterfaceService()

    //    Users
    fun getUsers(responseData: (List<UsersResponseItem>?, String?) -> Unit) {
        apiInterface.getUsers().enqueue(object : Callback<List<UsersResponseItem>> {
            override fun onResponse(
                call: Call<List<UsersResponseItem>>, response: Response<List<UsersResponseItem>>
            ) {
                response.run {
                    if (isSuccessful) {
                        body()?.let { response ->
                            responseData.invoke(response, null)
                        } ?: let {
                            responseData.invoke(
                                null, App.getAppContext().getString(R.string.somethingWrong)
                            )
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<List<UsersResponseItem>?>, t: Throwable
            ) {
                responseData.invoke(null, t.message.toString())
            }
        })
    }

    //    Posts
    fun getPosts(userId: Int? = null, responseData: (List<PostsResponseItem>?, String?) -> Unit) {
        val service = if (userId == null) {
            apiInterface.getPosts()
        } else {
            apiInterface.getPosts(userId)
        }

        service.enqueue(object : Callback<List<PostsResponseItem>> {
            override fun onResponse(
                call: Call<List<PostsResponseItem>>, response: Response<List<PostsResponseItem>>
            ) {
                response.run {
                    if (isSuccessful) {
                        body()?.let { response ->
                            responseData.invoke(response, null)
                        } ?: let {
                            responseData.invoke(
                                null, App.getAppContext().getString(R.string.somethingWrong)
                            )
                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<List<PostsResponseItem>?>, t: Throwable
            ) {
                responseData.invoke(null, t.message.toString())
            }
        })
    }

    companion object {
        private var instance: ItemRepository? = null
        fun getInstance(): ItemRepository {
            if (instance == null) instance = ItemRepository()
            return instance!!
        }
    }
}