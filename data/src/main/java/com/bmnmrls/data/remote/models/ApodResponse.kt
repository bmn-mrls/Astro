package com.bmnmrls.data.remote.models

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("explanation")
    val description: String? = null,
    @SerializedName("hdurl")
    val hdUrl: String? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("service_version")
    val serviceVersion: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null
)
