package hr.ferit.matijasokol.factorynewsreader.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.ferit.matijasokol.factorynewsreader.app.App
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem

@Database(version = 1, entities = [NewsItem::class], exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao(): NewsDao

    companion object{
        private const val NAME = "NEWS_DATABASE"
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(): NewsDatabase {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    App.instance,
                    NewsDatabase::class.java,
                    NAME
                )
                    .build()
            }
            return INSTANCE as NewsDatabase
        }

    }
}