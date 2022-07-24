package com.zain.dda.di

import com.zain.dda.data.repository.ArticleRepository
import com.zain.dda.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideArticleRepository(articleRepository: ArticleRepositoryImpl): ArticleRepository
}