package hr.ferit.matijasokol.factorynewsreader.presenters

import android.util.Log
import hr.ferit.matijasokol.factorynewsreader.common.RESPONSE_OK
import hr.ferit.matijasokol.factorynewsreader.database.repository.NewsRepositoryImpl
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.networking.interactor.NewsInteractorImpl
import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse
import hr.ferit.matijasokol.factorynewsreader.prefs.PreferenceManager
import hr.ferit.matijasokol.factorynewsreader.ui.activities.mainActivity.MainActivityContract
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(private val view: MainActivityContract.View) : MainActivityContract.Presenter {

    private val TAG = "[DEBUG] MainActivityPre"
    private val FETCH_TIME_KEY = "fetch time"

    override fun checkForFetchTime() {
        CoroutineScope(IO).launch {
            val lastFetchTimeSeconds = PreferenceManager.getLong(FETCH_TIME_KEY, 0L)
            withContext(Main) {
                val currentSeconds = System.currentTimeMillis() / 1000

                if ((currentSeconds - lastFetchTimeSeconds) > 300) {
                    Log.d(TAG, "checkForFetchTime: fetching...")
                    view.timeToFetchNewData()
                } else {
                    Log.d(TAG, "checkForFetchTime: repository")
                    view.noNeedForFetchingNewData()
                }
            }
        }
    }

    override fun startNetworking() {
        NewsInteractorImpl.getNews(callback)
    }

    private val callback: Callback<NewsResponse> = object : Callback<NewsResponse> {
        override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
            view.onRequestFailed(t)
        }

        override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> view.onRequestReponseOk(response.body())
                    else -> view.onRequestReponseWentWrong(response.code())
                }
            }
        }
    }

    override fun getDataFromRepository(){
        CoroutineScope(IO).launch {
            val data = NewsRepositoryImpl.getAll()
            withContext(Main) {
                view.onGetDataFromRepository(data)
            }
        }
    }

    override fun saveDataToRepostiory(data: List<NewsItem>) {
        Log.d(TAG, "saveDataToRepostiory")
        CoroutineScope(IO).launch {
            NewsRepositoryImpl.deleteAll()
            NewsRepositoryImpl.insertAll(data)
        }
    }

    override fun setNewFetchTime() {
        Log.d(TAG, "setNewFetchTime")
        CoroutineScope(IO).launch {
            PreferenceManager.saveLong(FETCH_TIME_KEY, System.currentTimeMillis() / 1000)
        }
    }
}