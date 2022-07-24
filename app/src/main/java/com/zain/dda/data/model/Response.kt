package com.zain.dda.data.model

data class Response(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)