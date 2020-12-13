package ru.alexbox.arch_gb_ko.view

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import ru.alexbox.arch_gb_ko.datasource.DataSourceLocal
import ru.alexbox.arch_gb_ko.datasource.DataSourceRemote
import ru.alexbox.arch_gb_ko.data.DataModel
import ru.alexbox.arch_gb_ko.presenter.IPresenter
import ru.alexbox.arch_gb_ko.repository.RepositoryImplementation
import ru.alexbox.arch_gb_ko.rx.ShedulerProvider

class MainPresenterImpl<T : DataModel, V : IView>(

    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable : CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider : ShedulerProvider = ShedulerProvider()
) : IPresenter<T, V> {
    private var currentView : V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
                currentView = null
            }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { currentView?.renderData(
            DataModel.Loading(
                null
            )
        ) }

    private fun getObserver(): DisposableObserver<DataModel> {
        return object : DisposableObserver<DataModel>() {

            override fun onNext(data: DataModel) {
                currentView?.renderData(data)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(
                    DataModel.Error(
                        e
                    )
                )
            }

            override fun onComplete() {
            }
        }
    }

}