package com.arief.news.module

import com.arief.news.repository.ApiNewsRepo
import org.koin.dsl.module

val repoModule = module {
    single { ApiNewsRepo(get()) }
}