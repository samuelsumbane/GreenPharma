package com.samuelsumbane.greenpharma

import com.samuelsumbane.greenpharma.features.providers.domain.ProviderService
import com.samuelsumbane.greenpharma.features.providers.presentation.providerRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.get

fun Application.configureRounting() {
    // Koin
    val providerService: ProviderService = get()

    routing {
        providerRoutes(providerService)
    }
}