package com.samuelsumbane.greenpharma

import com.samuelsumbane.greenpharma.features.clients.domain.ClientService
import com.samuelsumbane.greenpharma.features.clients.presentation.clientRoute
import com.samuelsumbane.greenpharma.features.products.domain.ProductService
import com.samuelsumbane.greenpharma.features.products.presentation.productRoutes
import com.samuelsumbane.greenpharma.features.providers.domain.ProviderService
import com.samuelsumbane.greenpharma.features.providers.presentation.providerRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.get

fun Application.configureRounting() {
    // Koin
    val providerService: ProviderService = get()
    val clientService: ClientService = get()
    val productService: ProductService = get()

    routing {
        providerRoutes(providerService)
        clientRoute(clientService)
        productRoutes(productService)
    }
}