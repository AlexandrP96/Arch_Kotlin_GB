package ru.alexbox.arch_gb_ko.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.alexbox.arch_gb_ko.data.DataModel
import ru.alexbox.arch_gb_ko.presenter.IPresenter

abstract class BaseActivity<T: DataModel> : AppCompatActivity(), IView {

    protected lateinit var presenter: IPresenter<T, IView>

    protected abstract fun createPresenter(): IPresenter<T, IView>
    abstract override fun renderData(appstate: DataModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}