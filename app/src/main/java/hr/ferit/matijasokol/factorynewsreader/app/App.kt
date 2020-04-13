package hr.ferit.matijasokol.factorynewsreader.app

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        lateinit var instance: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}