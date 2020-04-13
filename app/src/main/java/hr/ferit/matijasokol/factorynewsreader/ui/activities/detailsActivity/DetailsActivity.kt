package hr.ferit.matijasokol.factorynewsreader.ui.activities.detailsActivity

import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import hr.ferit.matijasokol.factorynewsreader.R
import hr.ferit.matijasokol.factorynewsreader.common.INTENT_KEY_ITEM
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.presenters.DetailsActivityPresenter
import hr.ferit.matijasokol.factorynewsreader.ui.activities.base.BaseActivity
import hr.ferit.matijasokol.factorynewsreader.ui.adapters.NewsPagerAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailsActivity : BaseActivity(R.layout.activity_details), DetailsActivityContract.View {

    private val presenter: DetailsActivityContract.Presenter by lazy { DetailsActivityPresenter(this) }
    private val pagerAdapter by lazy { NewsPagerAdapter(supportFragmentManager) }
    private var position = 0

    override fun setUpUi() {
        setToolbar()
        position = intent.getIntExtra(INTENT_KEY_ITEM, -1)
        if (position != -1) {
            setUpViewPager()
        } else {
            onBackPressed()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpViewPager() {
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(pageChangeListener)
        presenter.getDataFromRepository()
    }

    override fun onGetDataFromRepository(data: List<NewsItem>) {
        pagerAdapter.setNews(data)
        viewPager.currentItem = position
        supportActionBar?.title = data[position].title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) { supportActionBar?.title = pagerAdapter.getNews()[position].title }
    }
}
