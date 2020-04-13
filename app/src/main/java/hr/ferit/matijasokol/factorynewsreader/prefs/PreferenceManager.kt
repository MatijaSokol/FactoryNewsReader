package hr.ferit.matijasokol.factorynewsreader.prefs

import android.content.Context
import hr.ferit.matijasokol.factorynewsreader.app.App

object PreferenceManager {

    private const val PREFS = "prefs"
    private val prefs = App.instance.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun saveLong(key: String, value: Long) {
        prefs.edit().apply {
            putLong(key, value)
            apply()
        }
    }

    fun getLong(key: String, defaultValue: Long): Long = prefs.getLong(key, defaultValue)
}