package com.floward.assignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.floward.assignment.R
import com.floward.assignment.modal.posts.PostsResponseItem

class PostAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    private val postsList: ArrayList<PostsResponseItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<PostsResponseItem>) {
        postsList.clear()
        postsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var layout_postItem = view.findViewById(R.id.layout_PostItem) as ConstraintLayout
        var tvtitle = view.findViewById(R.id.tv_title) as TextView
        var tvbody = view.findViewById(R.id.tv_body) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = postsList[position]
        val title = "Title: ${item.title}"
        val body = item.body


        holder.tvtitle.text = title
        holder.tvbody.text = body


        holder.layout_postItem.setOnClickListener {
            onItemClickListener.clickListener(item)
        }

    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    interface OnItemClickListener {
        fun clickListener(post: PostsResponseItem)
    }
}