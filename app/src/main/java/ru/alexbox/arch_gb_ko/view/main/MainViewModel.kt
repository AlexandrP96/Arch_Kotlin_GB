package ru.alexbox.arch_gb_ko.view.main

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import ru.alexbox.arch_gb_ko.util.parseSearchResults
import io.reactivex.observers.DisposableObserver
import ru.alexbox.arch_gb_ko.model.data.AppState
import ru.alexbox.arch_gb_ko.view_model.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
        BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe() : LiveData<AppState> {
        return liveDataForViewObserve
    }

    private fun doOnSubscribe() : (Disposable) -> Unit = {liveDataForViewObserve.value = AppState.Loading(null)}

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(t: AppState) {
                appState = parseSearchResults(t)
                liveDataForViewObserve.value = appState
            }
            override fun onError(e: Throwable) { liveDataForViewObserve.value = AppState.Error(e) }
            override fun onComplete() {}
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(shedulerProvider.io()).observeOn(shedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe()).subscribeWith(getObserver())
        )
    }
}