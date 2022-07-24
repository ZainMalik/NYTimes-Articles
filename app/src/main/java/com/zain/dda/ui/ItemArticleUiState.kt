package com.zain.dda.ui

import com.zain.dda.common.BaseUiState
import com.zain.dda.data.model.Result
import com.zain.dda.util.Constants

data class ArticleItemUiState(private val result: Result) : BaseUiState() {

    fun getImageUrl() = if(!result.media.isEmpty()) {
        result.media[0].`media-metadata`[0].url
    } else {
        ""
    }

    fun getImagePosterUrl() = if(!result.media.isEmpty()) {
        result.media[0].`media-metadata`[2].url
    } else {
        ""
    }

    fun getName() = result.title

    fun getOverview() = result.abstract

    fun getDate() = result.published_date

}