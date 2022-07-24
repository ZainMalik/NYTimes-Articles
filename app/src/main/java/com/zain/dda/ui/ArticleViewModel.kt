package com.zain.dda.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.zain.dda.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(articleRepository: ArticleRepository) : ViewModel() {
    val articleItemsUiStates = articleRepository.getArticles()
        .map { pagingData ->
            pagingData.map { result -> ArticleItemUiState(result) }
        }.cachedIn(viewModelScope)
}
