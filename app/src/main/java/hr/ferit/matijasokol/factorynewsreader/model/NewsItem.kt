package hr.ferit.matijasokol.factorynewsreader.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hr.ferit.matijasokol.factorynewsreader.R
import hr.ferit.matijasokol.factorynewsreader.app.App
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "News")
data class NewsItem (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @SerializedName("author") val author: String? = App.instance.getString(R.string.unknown),
    @SerializedName("title") var title: String? = App.instance.getString(R.string.unknown),
    @SerializedName("description") val description: String? = App.instance.getString(R.string.unknown),
    @SerializedName("url") val url: String? = App.instance.getString(R.string.unknown),
    @SerializedName("urlToImage") val imageUrl: String? = App.instance.getString(R.string.unknown),
    @SerializedName("publishedAt") val published: String? = App.instance.getString(R.string.unknown)
) : Parcelable