package it.laface.news.domain

import java.util.Date

data class Article(
    val title: String,
    val date: Date,
    val htmlContent: String,
    val imageUrl: String,
    val link: String
)