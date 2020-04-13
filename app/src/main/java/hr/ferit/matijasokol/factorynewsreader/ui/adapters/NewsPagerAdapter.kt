package hr.ferit.matijasokol.factorynewsreader.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import hr.ferit.matijasokol.factorynewsreader.ui.fragments.NewsDetailsFragment

class NewsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val newsList = mutableListOf<NewsItem>()

    fun setNews(newsItems: List<NewsItem>) {
        this.newsList.clear()
        this.newsList.addAll(newsItems)
        notifyDataSetChanged()
    }

    fun getNews() = newsList

    override fun getItem(position: Int): Fragment = NewsDetailsFragment.newInstance(newsList[position])

    override fun getCount(): Int = newsList.size
}