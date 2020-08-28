package it.laface.news.api

import it.laface.base.NetworkResult
import it.laface.networking.toNetworkResult
import it.laface.news.domain.Article
import it.laface.news.domain.NewsDataSource

class NewsMapper(private val service: NewsService) : NewsDataSource {

    override suspend fun getNews(limit: Int, offset: Int): NetworkResult<List<Article>> =
        service.getNews(limit, offset).toNetworkResult { response ->
            response.posts.map { post ->
                Article(
                    title = post.title,
                    date = post.date,
                    htmlContent = post.content,
                    imageUrl = post.imageUrl,
                    link = post.meta.link
                )
            }
        }
}