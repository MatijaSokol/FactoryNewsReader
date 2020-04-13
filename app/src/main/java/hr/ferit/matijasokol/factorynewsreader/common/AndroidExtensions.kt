package hr.ferit.matijasokol.factorynewsreader.common

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.squareup.picasso.Picasso
import hr.ferit.matijasokol.factorynewsreader.R
import hr.ferit.matijasokol.factorynewsreader.app.App
import org.w3c.dom.Text

fun Context.displayToast(message: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun Context.showAlertDialog(title: String, message: String) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok) {_, _ ->  null}
        .show()
}

fun ImageView.loadImage(imageUrl: String?) {
    if (imageUrl != null && imageUrl.isNotEmpty()) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(this)
    }
}

fun TextView.setTextIfValid(text: String?) {
    if (text != null && text.isNotEmpty()) {
        this.text = text
    } else {
        this.text = App.instance.getString(R.string.unknown)
    }
}

fun TextView.appendTextIfValid(text: String?) {
    if (text != null && text.isNotEmpty()) {
        this.append(text)
    } else {
        this.append(App.instance.getString(R.string.unknown))
    }
}