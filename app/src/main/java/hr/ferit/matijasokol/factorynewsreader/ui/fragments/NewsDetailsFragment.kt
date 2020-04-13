package hr.ferit.matijasokol.factorynewsreader.ui.fragments

import android.os.Bundle
import hr.ferit.matijasokol.factorynewsreader.R
import hr.ferit.matijasokol.factorynewsreader.common.EXTRA_KEY
import hr.ferit.matijasokol.factorynewsreader.common.appendTextIfValid
import hr.ferit.matijasokol.factorynewsreader.common.loadImage
import hr.ferit.matijasokol.factorynewsreader.common.setTextIfValid
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : BaseFragment(R.layout.fragment_news_details) {

    companion object {
        fun newInstance(newsItem: NewsItem): NewsDetailsFragment {
            val fragment = NewsDetailsFragment()
            Bundle().apply {
                putParcelable(EXTRA_KEY, newsItem)
                fragment.arguments = this
            }
            return fragment
        }
    }

    override fun setUpUi() {
        setView()
    }

    private fun setView() {
        arguments?.getParcelable<NewsItem>(EXTRA_KEY)?.let { newsItem ->
            imageDetails.loadImage(newsItem.imageUrl)
            titleDetails.setTextIfValid(newsItem.title)
            descriptionDetails.setTextIfValid(newsItem.description)
            url.setTextIfValid(newsItem.url)
            publishedDetails.appendTextIfValid(newsItem.published)
            authorDetails.appendTextIfValid(newsItem.author)
        }
    }
}