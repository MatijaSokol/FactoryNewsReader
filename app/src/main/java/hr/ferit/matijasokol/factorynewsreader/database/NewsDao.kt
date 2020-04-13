package hr.ferit.matijasokol.factorynewsreader.database

import androidx.room.*
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newsItem: NewsItem)

    @Delete
    suspend fun delete(newsItem: NewsItem)

    @Query("DELETE FROM News")
    suspend fun deleteAll()

    @Query("SELECT * FROM News")
    suspend fun getAll() : List<NewsItem>

    @Query("SELECT * FROM News WHERE id = :id")
    suspend fun getItemById(id: Long) : NewsItem
}