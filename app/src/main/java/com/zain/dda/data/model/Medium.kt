package com.zain.dda.data.model

import kotlinx.serialization.SerialName

data class Medium(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    val approved_for_syndication: Int,
    val `media-metadata`: List<MediaMetadatum>
) {

}