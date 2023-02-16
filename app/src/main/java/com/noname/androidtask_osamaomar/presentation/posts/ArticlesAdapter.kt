package com.noname.androidtask_osamaomar.presentation.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.noname.androidtask_osamaomar.data.local.room.LocalPost
import com.noname.androidtask_osamaomar.databinding.ArticleListItemBinding
import com.noname.androidtask_osamaomar.models.Post

class ArticlesAdapter(private val context: Context,private val listener: OnItemClickListener) :
    PagingDataAdapter<LocalPost,ArticlesAdapter.AdapterViewHolder>(differCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            ArticleListItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.binding.articleTitle.text = getItem(position)?.title
        holder.binding.articleDate.text = getItem(position)?.publishedAt?: ""
        holder.binding.root.setOnClickListener {
            listener.onItemClick(getItem(position))
        }
    }

  inner  class AdapterViewHolder(val binding: ArticleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    object differCallback : DiffUtil.ItemCallback<LocalPost>() {
        override fun areItemsTheSame(oldItem: LocalPost, newItem: LocalPost): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: LocalPost, newItem: LocalPost): Boolean {
            return oldItem == newItem
        }
    }
}