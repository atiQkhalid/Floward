package com.floward.assignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.floward.assignment.R
import com.floward.assignment.extensions.loadImage
import com.floward.assignment.modal.users.UsersResponseItem
import com.mikhaellopez.circularimageview.CircularImageView

class UserAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private val usersList: ArrayList<UsersResponseItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<UsersResponseItem>) {
        usersList.clear()
        usersList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var layout_UserItem = view.findViewById(R.id.layout_UserItem) as ConstraintLayout
        var civIcon = view.findViewById(R.id.civ_user) as CircularImageView
        var tvName = view.findViewById(R.id.tv_userName) as TextView
        var tvPostCount = view.findViewById(R.id.tv_postCount) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = usersList[position]
        val name = "Name: ${item.name}"
        val count = "Post Count: ${item.postCount}"
        val icon = item.thumbnailUrl

        holder.tvName.text = name
        holder.tvPostCount.text = count
        holder.civIcon.loadImage(icon)


        holder.layout_UserItem.setOnClickListener {
            onItemClickListener.clickListener(item)
        }

    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    interface OnItemClickListener {
        fun clickListener(user: UsersResponseItem)
    }
}