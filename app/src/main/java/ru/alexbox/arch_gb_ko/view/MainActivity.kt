package ru.alexbox.arch_gb_ko.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.alexbox.arch_gb_ko.R
import ru.alexbox.arch_gb_ko.data.DataModel
import ru.alexbox.arch_gb_ko.data.SearchResult
import ru.alexbox.arch_gb_ko.presenter.IPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<DataModel>() {

    private var adapter: MainAdapter? = null

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: SearchResult) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun createPresenter(): IPresenter<DataModel, IView> {
        return MainPresenterImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonLogic(view: View) {
        val editText: EditText = findViewById(R.id.edit_text)
        val searchWord : String
        searchWord = editText.text.toString()
        presenter.getData(searchWord, true)

    }

    override fun renderData(appstate: DataModel) {
        when (appstate) {
            is DataModel.Success -> {
                val searchResult = appstate.data
                if (searchResult == null || searchResult.isEmpty()) {
                    showErrorScreen()
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
                        recycler_view.adapter = MainAdapter(onListItemClickListener, searchResult)
                    } else {
                        adapter!!.setData(searchResult)
                    }
                }
            }
            is DataModel.Loading -> {
                showViewLoading()
                if (appstate.progress != null) {
                    status_view.text = getString(R.string.status_loading)
                }
            }
            is DataModel.Error -> {
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


}