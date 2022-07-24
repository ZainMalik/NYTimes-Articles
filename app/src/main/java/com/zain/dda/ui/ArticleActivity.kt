package com.zain.dda.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.zain.dda.R
import com.zain.dda.common.FooterAdapter
import com.zain.dda.databinding.ActivityArticleBinding
import com.zain.dda.util.ext.collect
import com.zain.dda.util.ext.collectLast
import com.zain.dda.util.ext.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private val viewModel: ArticleViewModel by viewModels()

    @Inject
    lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setListener()
        setAdapter()
        collectLast(viewModel.articleItemsUiStates, ::setArticles)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article)
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { articlesAdapter.retry() }
    }


    private fun setAdapter() {
        collect(flow = articlesAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setArticlesUiState
        )
        binding.rvArticles.adapter = articlesAdapter.withLoadStateFooter(FooterAdapter(articlesAdapter::retry))

        articlesAdapter.setOnItemClickListener(object : ArticlesAdapter.onArticleItemClickListener {
            override fun onClick(position: Int, item: ArticleItemUiState?) {
//                Toast.makeText(applicationContext, "" + position + item, Toast.LENGTH_LONG).show()
                val intent = Intent(this@ArticleActivity, DetailsActivity::class.java)
                intent.putExtra("image", item?.getImageUrl())
                intent.putExtra("poster", item?.getImagePosterUrl())
                intent.putExtra("name", item?.getName())
                intent.putExtra("overview", item?.getOverview())
                intent.putExtra("date", item?.getDate())
                startActivity(intent)
            }
        })

    }

    private fun setArticlesUiState(loadState: LoadState) {
        binding.executeWithAction {
            articlesUiState = ArticleUiState(loadState)
        }
    }

    private suspend fun setArticles(articleItemsPagingData: PagingData<ArticleItemUiState>) {
        articlesAdapter.submitData(articleItemsPagingData)
    }

}