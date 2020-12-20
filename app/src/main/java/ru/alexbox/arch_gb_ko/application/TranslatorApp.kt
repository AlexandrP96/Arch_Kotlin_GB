package ru.alexbox.arch_gb_ko.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.alexbox.arch_gb_ko.di.application
import ru.alexbox.arch_gb_ko.di.historyScreen
import ru.alexbox.arch_gb_ko.di.mainScreen

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin {
           androidContext(applicationContext)
           modules(listOf(application, mainScreen, historyScreen))
       }
    }

}