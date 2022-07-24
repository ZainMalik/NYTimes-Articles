package com.zain.dda.di

import com.zain.dda.ui.ArticlesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideArticlesAdapter(): ArticlesAdapter = ArticlesAdapter()
}