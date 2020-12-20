package ru.alexbox.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.alexbox.core.view_model.BaseViewModel
import ru.alexbox.core.view_model.IInteractor
import ru.alexbox.model.data.DataModel
import ru.alexbox.model.data.SearchResult
import ru.alexbox.utils.isOnline
import ru.alexbox.utils.ui.AlertDialogFragment

private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"

abstract class BaseActivity<T : DataModel, I : IInteractor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun renderData(dataModel : T) {
        when (dataModel) {
            is DataModel.Success -> {
                dataModel.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.status_error),
                            getString(R.string.status_error)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is DataModel.Loading -> {

            }
            is DataModel.Error -> {

                showAlertDialog(getString(R.string.status_error), dataModel.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.status_error),
            getString(R.string.status_internet_error)
        )
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToAdapter(data: List<SearchResult>)
}