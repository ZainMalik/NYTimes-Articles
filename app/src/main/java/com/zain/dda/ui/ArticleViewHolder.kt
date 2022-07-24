package com.zain.dda.ui

import androidx.recyclerview.widget.RecyclerView
import com.zain.dda.databinding.ItemArticleBinding
import com.zain.dda.util.ext.executeWithAction

class ArticleViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(articleItemUiState: ArticleItemUiState) {
        binding.executeWithAction {
            this.articleItemUiState = articleItemUiState
        }

    }
}