package com.samuelsumbane.greenpharma.features.providers.presentation

import com.samuelsumbane.greenpharma.features.providers.domain.ProviderService
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.providerRoutes(service: ProviderService) {
    route("/providers") {

        get {
           val allProviders = service.listAllProviders()
           call.respond(HttpStatusCode.OK, allProviders)
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
        }
    }
}