package hr.ferit.matijasokol.factorynewsreader.networking.response

import com.google.gson.annotations.SerializedName
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem

data class NewsResponse (@SerializedName("articles") val news: MutableList<NewsItem> = mutableListOf())