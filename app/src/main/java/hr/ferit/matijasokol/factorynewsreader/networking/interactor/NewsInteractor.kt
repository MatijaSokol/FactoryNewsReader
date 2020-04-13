package hr.ferit.matijasokol.factorynewsreader.networking.interactor

import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse
import retrofit2.Callback

interface NewsInteractor {

    fun getNews(newsCallback: Callback<NewsResponse>)
}