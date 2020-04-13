package hr.ferit.matijasokol.factorynewsreader.ui.fragments.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutResourceId: Int) : Fragment(layoutResourceId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    abstract fun setUpUi()
}