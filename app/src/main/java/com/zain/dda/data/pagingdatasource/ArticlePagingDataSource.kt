package com.zain.dda.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zain.dda.data.model.Result
import com.zain.dda.network.Service
import com.zain.dda.util.Constants

class ArticlePagingDataSource(private val service: Service) :
    PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = 0
        return try {
            val response = service.getArticles(Constants.API_KEY)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = null
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}
