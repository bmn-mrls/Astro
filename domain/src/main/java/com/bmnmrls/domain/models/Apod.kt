package com.bmnmrls.domain.models

data class Apod(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdUrl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String
)