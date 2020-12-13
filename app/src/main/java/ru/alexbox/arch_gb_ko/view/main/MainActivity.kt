package ru.alexbox.arch_gb_ko.view.main

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import ru.alexbox.arch_gb_ko.R
import kotlinx.android.synthetic.main.activity_main.*
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.model.data.DataModel
import ru.alexbox.arch_gb_ko.view.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    override lateinit var model: MainViewModel

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }


    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                    Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = viewModelFactory.create(MainViewModel::class.java)
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })

        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = adapter
    }

    fun buttonLogic(view: View) {
        val editText: EditText = findViewById(R.id.edit_text)
        val searchWord : String
        searchWord = editText.text.toString()
        model.getData(searchWord, isNetworkAvailable)

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewSuccess()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showErrorScreen()
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
            }
            is AppState.Error -> {
                showErrorScreen()
            }
        }
    }


    private fun showErrorScreen() {
        status_view.text = getString(R.string.status_error)
    }

    private fun showViewSuccess() {
        status_view.text = getString(R.string.status_success)
    }

    private fun showViewLoading() {
        status_view.text = getString(R.string.status_loading)
    }

    private fun showErrorInternet() {
        status_view.text = getString(R.string.empty_server_responce)
    }

}