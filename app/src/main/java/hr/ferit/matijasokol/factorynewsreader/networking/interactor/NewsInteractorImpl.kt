package hr.ferit.matijasokol.factorynewsreader.networking.interactor

import hr.ferit.matijasokol.factorynewsreader.common.*
import hr.ferit.matijasokol.factorynewsreader.networking.BackendFactory
import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

object NewsInteractorImpl : NewsInteractor {

    private val apiService = BackendFactory.getService()
    private val map = mapOf(Pair(SOURCE_KEY, SOURCE_VALUE), Pair(SORT_BY_KEY, SORT_BY_VALUE), Pair(API_KEY_KEY, API_KEY_VALUE))

    override suspend fun getNews(): Response<NewsResponse> = apiService.getNews(map)
}