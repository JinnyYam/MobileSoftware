package com.example.hu_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hu_project.R
import com.example.hu_project.databinding.ItemPostBinding
import com.example.hu_project.model.main_data

class HorizontalPostAdapter(private val posts: List<main_data.Post>) :
    RecyclerView.Adapter<HorizontalPostAdapter.PostViewHolder>() {

        inner class PostViewHolder(private val binding: ItemPostBinding) :
            RecyclerView.ViewHolder(binding.root) {

                // .load(post.imageUrl)를 예시를 위해 .load(R.drawable.whale)로 대체함.
                fun bind(post: main_data.Post) {
                    binding.postTitle.text = post.title
                    Glide.with(binding.postImage.context).load(R.drawable.whale).into(binding.postImage)
                }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context))
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size
}