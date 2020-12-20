package ru.alexbox.arch_gb_ko

sealed class AppState {
    data class Success(val data: List<ru.alexbox.model.data.DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}