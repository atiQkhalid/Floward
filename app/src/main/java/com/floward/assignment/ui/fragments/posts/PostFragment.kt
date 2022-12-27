package com.floward.assignment.ui.fragments.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.floward.assignment.R
import com.floward.assignment.adapter.PostAdapter
import com.floward.assignment.base.BaseFragment
import com.floward.assignment.databinding.FragmentPostsBinding
import com.floward.assignment.extensions.loadImage
import com.floward.assignment.extensions.showToastMsg
import com.floward.assignment.modal.posts.PostsResponseItem
import com.floward.assignment.ui.fragments.users.UserViewModel
import com.floward.assignment.utils.Const.HEADER
import com.floward.assignment.utils.Const.USER_ID
import com.floward.assignment.utils.Const.USER_NAME

class PostFragment : BaseFragment(), PostViewModel.View, PostAdapter.OnItemClickListener {

    private lateinit var binding: FragmentPostsBinding
    private val postViewModel: PostViewModel by viewModels()
    private var postAdapter: PostAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainActivity.supportActionBar?.title =
            "${arguments?.getString(USER_NAME)}'s ${resources.getString(R.string.posts)}"

        binding.ivCover.loadImage(arguments?.getString(HEADER))

        with(postViewModel) {
            this.attachView(this@PostFragment)
            arguments?.getInt(USER_ID)?.let { this.getPosts(it) }
        }

        postAdapter = PostAdapter(this)

        postAdapter.let {
            binding.rvUsersList.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
        }

        observePosts()
    }

    private fun observePosts() {
        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            postAdapter?.setItems(posts)
        }
    }

    override fun onUpdateResponse(message: String) {
        showToastMsg(message)
    }

    override fun showProgressBar() {
        dialoge.show(requireContext(), getString(R.string.loading))
    }

    override fun dismissProgressBar() {
        dialoge.dismiss()
    }

    override fun clickListener(post: PostsResponseItem) {
        showToastMsg("you clicked ${post.title} :)")
    }
}