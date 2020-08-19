package it.laface.network

import it.laface.api.NbaNews
import it.laface.domain.datasource.NewsDataSource
import it.laface.domain.model.Article
import it.laface.domain.network.NetworkResult

class NewsApiMapper(private val newsApi: NbaNews) :
    NewsDataSource {

    override suspend fun getNews(limit: Int, offset: Int): NetworkResult<List<Article>> {
        return newsApi.getNews(limit, offset).toNetworkResult { response ->
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
}
