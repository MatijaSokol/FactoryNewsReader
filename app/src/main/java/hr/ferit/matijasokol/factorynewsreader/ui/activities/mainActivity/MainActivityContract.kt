package hr.ferit.matijasokol.factorynewsreader.ui.activities.mainActivity

import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse

interface MainActivityContract {

    interface Presenter {
        fun startNetworking()
        fun getDataFromRepository()
        fun saveDataToRepostiory(data: List<NewsItem>)
        fun checkForFetchTime()
        fun setNewFetchTime()
    }

    interface View {
        fun onRequestReponseOk(newsResponse: NewsResponse?)
        fun onRequestReponseWentWrong(code: Int)
        fun onRequestFailed(throwable: Throwable)
        fun onGetDataFromRepository(data: List<NewsItem>)
        fun timeToFetchNewData()
        fun noNeedForFetchingNewData()
    }
}