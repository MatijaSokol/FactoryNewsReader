package hr.ferit.matijasokol.factorynewsreader.ui.activities.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(private val layoutResourceId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

        setUpUi()
    }

    abstract fun setUpUi()
}