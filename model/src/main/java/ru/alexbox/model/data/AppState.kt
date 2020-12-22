package ru.alexbox.model.data

// here is init commit

// app
// buildSource - ready
// core - ready
// historyScreen - ready
// model - ready
// repository - ready
// utils - ready
// general gradle settings (all)

sealed class AppState {
    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}