package hr.ferit.matijasokol.factorynewsreader.ui.activities.detailsActivity

import hr.ferit.matijasokol.factorynewsreader.model.NewsItem

interface DetailsActivityContract {

    interface View {
        fun onGetDataFromRepository(data: List<NewsItem>)
    }

    interface Presenter {
        fun getDataFromRepository()
    }
}