package com.floward.assignment.ui.fragments.users

import androidx.lifecycle.MutableLiveData
import com.floward.assignment.base.BaseViewModel
import com.floward.assignment.modal.posts.PostsResponseItem
import com.floward.assignment.modal.users.UsersResponseItem

class UserViewModel : BaseViewModel<UserViewModel.View>() {

    val users = MutableLiveData<List<UsersResponseItem>>()
    private val _users: MutableList<UsersResponseItem> = ArrayList()
    private val _posts: MutableList<PostsResponseItem> = ArrayList()

    //    get Posts list
    fun getPosts() {
        getView().showProgressBar()
        //CAll API/Repository
        itemRepository.getPosts { postsResponse, error ->
            if (error == null) {
                _posts.addAll(postsResponse as ArrayList)
                getUsers()
            } else {
                getView().dismissProgressBar()
            }
        }
    }

    //    get User list
    private fun getUsers() {
        //CAll API/Repository
        itemRepository.getUsers { userResponse, error ->
            getView().dismissProgressBar()
            if (error == null) {
                getPostCount(userResponse as ArrayList)
            }
        }
    }

    private fun getPostCount(userList: ArrayList<UsersResponseItem>) {
        userList.forEach { userData ->
            val filteredPosts = _posts.filter { post -> post.userId == userData.userId }
            val user = userData.also {
                it.postCount = filteredPosts.size
            }
            _users.add(user)
        }
        users.value = _users
    }

    interface View {
        fun onUpdateResponse(message: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}