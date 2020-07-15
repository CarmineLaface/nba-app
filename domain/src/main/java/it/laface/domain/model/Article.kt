package it.laface.domain.model

import java.util.Date

class Article(
    val title: String,
    val date: Date,
    val htmlContent: String,
    val imageUrl: String,
    val link: String
)
