package com.zain.dda.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zain.dda.data.model.Result
import com.zain.dda.data.pagingdatasource.ArticlePagingDataSource
import com.zain.dda.network.Service
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val service: Service
) : ArticleRepository {
    override fun getArticles(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { ArticlePagingDataSource(service) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
