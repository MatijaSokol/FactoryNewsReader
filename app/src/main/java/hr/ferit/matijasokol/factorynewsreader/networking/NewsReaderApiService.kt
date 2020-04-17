package hr.ferit.matijasokol.factorynewsreader.networking

import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

const val BASE_URL = "https://newsapi.org/v1/"

interface NewsReaderApiService{

    @GET("articles")
    suspend fun getNews(@QueryMap parameters: Map<String, String>) : Response<NewsResponse>
}