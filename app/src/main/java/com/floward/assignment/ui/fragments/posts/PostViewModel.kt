package com.floward.assignment.ui.fragments.posts

import androidx.lifecycle.MutableLiveData
import com.floward.assignment.base.BaseViewModel
import com.floward.assignment.modal.posts.PostsResponseItem
import com.floward.assignment.modal.users.UsersResponseItem
import java.sql.RowId

class PostViewModel : BaseViewModel<PostViewModel.View>() {

    val posts = MutableLiveData<List<PostsResponseItem>>()

    //    get Posts list
    fun getPosts(userId: Int) {
        getView().showProgressBar()
        //CAll API/Repository
        itemRepository.getPosts(userId) { postsResponse, error ->
            if (error == null) {
                getView().dismissProgressBar()
                posts.value = postsResponse
            } else {
                getView().dismissProgressBar()
            }
        }
    }

    interface View {
        fun onUpdateResponse(message: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}