package it.laface.news.domain

data class Article(
    val title: String,
    val htmlContent: String,
    val imageUrl: String,
    val link: String
)
