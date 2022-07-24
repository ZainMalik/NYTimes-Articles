package com.zain.dda.data.repository

import androidx.paging.PagingData
import com.zain.dda.data.model.Result
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(): Flow<PagingData<Result>>
}