package hr.ferit.matijasokol.factorynewsreader.database.repository

import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse

interface NewsRepository{

    suspend fun insert(newsItem: NewsItem)

    suspend fun insertAll(newsItems: List<NewsItem>)

    suspend fun insertAll(newsItems: NewsResponse)

    suspend fun delete(newsItem: NewsItem)

    suspend fun deleteAll()

    suspend fun getAll(): List<NewsItem>

    suspend fun getItemById(id: Long): NewsItem
}