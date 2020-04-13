package hr.ferit.matijasokol.factorynewsreader.presenters

import hr.ferit.matijasokol.factorynewsreader.database.repository.NewsRepositoryImpl
import hr.ferit.matijasokol.factorynewsreader.ui.activities.detailsActivity.DetailsActivityContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivityPresenter(private val view: DetailsActivityContract.View) : DetailsActivityContract.Presenter {

    override fun getDataFromRepository(){
        CoroutineScope(IO).launch {
            val data = NewsRepositoryImpl.getAll()
            withContext(Main) {
                view.onGetDataFromRepository(data)
            }
        }
    }
}