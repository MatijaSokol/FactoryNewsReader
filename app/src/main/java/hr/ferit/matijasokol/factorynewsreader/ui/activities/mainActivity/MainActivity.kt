package hr.ferit.matijasokol.factorynewsreader.ui.activities.mainActivity

import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.matijasokol.factorynewsreader.R
import hr.ferit.matijasokol.factorynewsreader.common.*
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.networking.response.NewsResponse
import hr.ferit.matijasokol.factorynewsreader.presenters.MainActivityPresenter
import hr.ferit.matijasokol.factorynewsreader.ui.activities.detailsActivity.DetailsActivity
import hr.ferit.matijasokol.factorynewsreader.ui.activities.base.BaseActivity
import hr.ferit.matijasokol.factorynewsreader.ui.adapters.NewsItemAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity(R.layout.activity_main), MainActivityContract.View {

    private val TAG = "[DEBUG] MainActivity"
    private val newsAdapter by lazy { NewsItemAdapter { onItemClicked(it) } }
    private val presenter: MainActivityContract.Presenter by lazy { MainActivityPresenter(this) }

    override fun setUpUi() {
        setSupportActionBar(toolbar as Toolbar)
        setRecycler()
        setUpSwiper()
        presenter.checkForFetchTime()
    }

    private fun setRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            setHasFixedSize(true)
        }
    }

    private fun setUpSwiper() {
        swiper.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE)
        swiper.setOnRefreshListener {
            startNetworking()
        }
    }

    private fun onItemClicked(position: Int) {
        Intent(this, DetailsActivity::class.java).also {
            it.putExtra(INTENT_KEY_ITEM, position)
            startActivity(it)
        }
    }

    override fun timeToFetchNewData() {
        startNetworking()
    }

    override fun noNeedForFetchingNewData() {
        presenter.getDataFromRepository()
    }

    private fun startNetworking() {
        if (!swiper.isRefreshing) {
            progressBar.visible()
        }
        presenter.startNetworking()
    }

    override fun onRequestReponseOk(newsResponse: NewsResponse?) {
        Log.d(TAG, "onRequestReponseOk")
        progressBar.gone()
        closeSwiperIfRefreshing()
        if (newsResponse == null) {
            checkForEmptyAdapter()
        } else {
            Log.d(TAG, "onRequestReponseOk: new data set")
            newsAdapter.setData(newsResponse.news)
            presenter.saveDataToRepostiory(newsResponse.news)
        }
        presenter.setNewFetchTime()
    }

    private fun checkForEmptyAdapter() {
        if (newsAdapter.getData().isEmpty()) {
            Log.d(TAG, "checkForEmptyAdapter")
            presenter.getDataFromRepository()
        }
    }

    override fun onRequestReponseWentWrong(code: Int) {
        Log.d(TAG, "onRequestReponseWentWrong: $code")
        progressBar.gone()
        closeSwiperIfRefreshing()
        checkForEmptyAdapter()
        showAlertDialog(getString(R.string.error), getString(R.string.something_went_wrong))
    }

    override fun onRequestFailed(throwable: Throwable) {
        Log.d(TAG, "onRequestFailed: ${throwable.message}")
        progressBar.gone()
        closeSwiperIfRefreshing()
        checkForEmptyAdapter()
        showAlertDialog(getString(R.string.error), getString(R.string.request_failed))
    }

    override fun onGetDataFromRepository(data: List<NewsItem>) {
        Log.d(TAG, "onGetDataFromRepository: adapter set from repository")
        newsAdapter.setData(data)
    }

    private fun closeSwiperIfRefreshing() {
        if (swiper.isRefreshing) {
            swiper.isRefreshing = false
        }
    }
}
