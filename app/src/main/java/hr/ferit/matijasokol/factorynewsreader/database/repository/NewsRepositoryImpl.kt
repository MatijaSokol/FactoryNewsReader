package hr.ferit.matijasokol.factorynewsreader.database.repository

import hr.ferit.matijasokol.factorynewsreader.database.NewsDatabase
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse

object NewsRepositoryImpl : NewsRepository {

    private var newsDao = NewsDatabase.getInstance().newsDao()

    override suspend fun insert(newsItem: NewsItem) = newsDao.insert(newsItem)

    override suspend fun insertAll(newsItems: List<NewsItem>) = newsItems.forEach { newsDao.insert(it) }

    override suspend fun insertAll(newsItems: NewsResponse) = newsItems.news.forEach { newsDao.insert(it) }

    override suspend fun delete(newsItem: NewsItem) = newsDao.delete(newsItem)

    override suspend fun deleteAll() =  newsDao.deleteAll()

    override suspend fun getAll(): List<NewsItem> = newsDao.getAll()

    override suspend fun getItemById(id: Long): NewsItem = newsDao.getItemById(id)
}