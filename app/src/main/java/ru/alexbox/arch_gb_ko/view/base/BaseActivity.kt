package ru.alexbox.arch_gb_ko.view.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import ru.alexbox.arch_gb_ko.R
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.presenter.IPresenter
import ru.alexbox.arch_gb_ko.util.isOnline
import ru.alexbox.arch_gb_ko.view.IView
import ru.alexbox.arch_gb_ko.view_model.BaseViewModel
import ru.alexbox.arch_gb_ko.view_model.IInteractor

abstract class BaseActivity<T: AppState, I : IInteractor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistableBundle: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    // onStart
    // onStop

    protected fun showNoInternetConnectionMessage(): String {
     return getString(R.string.status_internet_error)
    }
}