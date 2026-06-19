package com.samuelsumbane.greenpharma.features.products.presentation

import com.samuelsumbane.greenpharma.features.products.domain.ProductService
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.productRoutes(service: ProductService) {

    route("/products") {

        get {
            val allProducts = service.getAllProducts()
            call.respond(HttpStatusCode.OK, allProducts.map { it })
        }
    }
}