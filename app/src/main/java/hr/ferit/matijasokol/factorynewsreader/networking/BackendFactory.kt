package hr.ferit.matijasokol.factorynewsreader.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendFactory {

    private var retrofit: Retrofit? = null
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val client: Retrofit? = if (retrofit == null) createRetrofit() else retrofit

    private fun createRetrofit(): Retrofit? =
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getService(): NewsReaderApiService = client!!.create(NewsReaderApiService::class.java)
}