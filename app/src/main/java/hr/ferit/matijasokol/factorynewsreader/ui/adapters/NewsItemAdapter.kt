package hr.ferit.matijasokol.factorynewsreader.ui.adapters

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.matijasokol.factorynewsreader.R
import hr.ferit.matijasokol.factorynewsreader.common.loadImage
import hr.ferit.matijasokol.factorynewsreader.model.NewsItem
import kotlinx.android.synthetic.main.news_item.view.*

class NewsItemAdapter(private val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<NewsItemAdapter.NewsItemViewHolder>() {

    private val data: MutableList<NewsItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun getItemCount(): Int = this.data.size

    override fun onBindViewHolder(viewHolder: NewsItemViewHolder, position: Int) {
        viewHolder.bind(this.data[position], onItemClicked)
    }

    fun setData(newsItems: List<NewsItem>){
        this.data.clear()
        this.data.addAll(newsItems)
        notifyDataSetChanged()
    }

    fun getData() = data

    class NewsItemViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView){

        fun bind(newsItem: NewsItem, onItemClicked: (Int) -> (Unit)){
            with(containerView) {
                val shader = LinearGradient(0f, description.paint.textSize * 2,
                    0f, description.paint.textSize,
                    intArrayOf(Color.GRAY, Color.BLACK), floatArrayOf(0f, 1f), Shader.TileMode.CLAMP)

                description.paint.shader = shader

                title.text = newsItem.title
                description.text = newsItem.description
                newsItem.imageUrl?.let {
                    image.loadImage(it)
                }
                setOnClickListener {
                    adapterPosition.let {
                        if (it != RecyclerView.NO_POSITION) {
                            onItemClicked(it)
                        }
                    }
                }
            }
        }
    }
}