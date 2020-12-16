package ru.alexbox.arch_gb_ko.view.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.alexbox.arch_gb_ko.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.util.isOnline
import ru.alexbox.arch_gb_ko.view.base.BaseActivity

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    // for init commit
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        if (recycler_view.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        fab_button.setOnClickListener(fabClickListener)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = adapter
    }

    private fun showLoading() { status_view.setText(R.string.status_loading) }
    private fun showWorking() { status_view.setText(R.string.status_none) }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Success -> {
                showWorking()
                val data = dataModel.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                            getString(R.string.status_error),
                            getString(R.string.empty_server_responce)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showLoading()
            }
            is AppState.Error -> {
                showWorking()
                showAlertDialog(getString(R.string.status_error), dataModel.error.message)
            }
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}