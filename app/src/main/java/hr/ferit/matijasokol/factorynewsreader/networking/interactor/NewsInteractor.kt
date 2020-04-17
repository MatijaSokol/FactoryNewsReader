package hr.ferit.matijasokol.factorynewsreader.networking.interactor

import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

interface NewsInteractor {

    suspend fun getNews(): Response<NewsResponse>
}