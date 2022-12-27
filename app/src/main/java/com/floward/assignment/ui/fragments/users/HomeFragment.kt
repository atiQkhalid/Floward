package com.floward.assignment.ui.fragments.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.floward.assignment.R
import com.floward.assignment.adapter.UserAdapter
import com.floward.assignment.base.BaseFragment
import com.floward.assignment.databinding.FragmentHomeBinding
import com.floward.assignment.extensions.replaceFragment
import com.floward.assignment.extensions.showToastMsg
import com.floward.assignment.modal.users.UsersResponseItem
import com.floward.assignment.ui.fragments.posts.PostFragment
import com.floward.assignment.utils.Const

class HomeFragment : BaseFragment(), UserViewModel.View, UserAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val homeUserViewModel: UserViewModel by viewModels()
    private var userAdapter: UserAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainActivity.supportActionBar?.title =
            resources.getString(R.string.Users)
        with(homeUserViewModel) {
            attachView(this@HomeFragment)
            this.getPosts()
        }

        userAdapter = UserAdapter(this)

        userAdapter.let {
            binding.rvUsersList.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
        }

        observeUsers()
    }

    private fun observeUsers() {
        homeUserViewModel.users.observe(viewLifecycleOwner) { users ->
            userAdapter?.setItems(users)
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

    override fun clickListener(user: UsersResponseItem) {
        val bundle = Bundle()

        bundle.putString(Const.USER_NAME, user.name)
        bundle.putInt(Const.USER_ID, user.userId)
        bundle.putString(Const.HEADER, user.url)

        replaceFragment(PostFragment(), bundle = bundle)
    }
}