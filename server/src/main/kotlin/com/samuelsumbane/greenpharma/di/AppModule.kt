package com.samuelsumbane.greenpharma.di

import com.samuelsumbane.greenpharma.features.providers.data.ProviderRepository
import com.samuelsumbane.greenpharma.features.providers.domain.ProviderService
import org.koin.dsl.module


val appModule = module {
    // Repositories
    single { ProviderRepository }

    // Services
    single { ProviderService(get()) }
}