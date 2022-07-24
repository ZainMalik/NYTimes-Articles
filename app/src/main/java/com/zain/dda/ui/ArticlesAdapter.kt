package com.zain.dda.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zain.dda.R
import com.zain.dda.databinding.ItemArticleBinding
import javax.inject.Inject

class ArticlesAdapter @Inject constructor() :
    PagingDataAdapter<ArticleItemUiState, ArticleViewHolder>(Comparator) {

    private var onItemClickListener: onArticleItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: onArticleItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface onArticleItemClickListener {
        fun onClick(position: Int, item: ArticleItemUiState?) //pass your object types.
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { articleItemUiState -> holder.bind(articleItemUiState) }
        holder.itemView.setOnClickListener{
            onItemClickListener?.onClick(position, getItem(position));
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val binding = inflate<ItemArticleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent,
            false
        )

        return ArticleViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<ArticleItemUiState>() {
        override fun areItemsTheSame(oldItem: ArticleItemUiState, newItem: ArticleItemUiState): Boolean {
            return oldItem.getName() == newItem.getName()
        }

        override fun areContentsTheSame(
            oldItem: ArticleItemUiState,
            newItem: ArticleItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}