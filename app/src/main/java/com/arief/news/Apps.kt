package com.arief.news

import android.app.Application
import com.arief.news.module.networkModule
import com.arief.news.module.repoModule
import com.arief.news.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Apps : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Apps)
            modules(listOf(networkModule, repoModule, viewModelModule))
        }
    }
}