package it.laface.news.api

import it.laface.base.NetworkResult
import it.laface.networking.toNetworkResult
import it.laface.news.domain.Article
import it.laface.news.domain.NewsDataSource

class NewsMapper(private val service: NewsService) : NewsDataSource {

    override suspend fun getNews(): NetworkResult<List<Article>> =
        service.getNews().toNetworkResult { response ->
            response.posts.map { post ->
                Article(
                    title = post.title,
                    htmlContent = post.content,
                    imageUrl = post.imageUrl,
                    link = post.meta.link
                )
            }
        }
}
