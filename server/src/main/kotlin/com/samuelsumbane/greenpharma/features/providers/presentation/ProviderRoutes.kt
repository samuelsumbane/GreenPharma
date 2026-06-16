package com.samuelsumbane.greenpharma.features.providers.presentation

import com.samuelsumbane.greenpharma.features.providers.domain.ProviderService
import com.samuelsumbane.greenpharma.model.ProviderRequest
import com.samuelsumbane.greenpharma.model.toDomain
import com.samuelsumbane.greenpharma.model.toResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.providerRoutes(service: ProviderService) {
    route("/providers") {

        get {
           val allProviders = service.listAllProviders()
           call.respond(HttpStatusCode.OK, allProviders)
        }

        get("{id}") {
            val id = call.parameters["id"]
            id?.let {
                val provider = service.findProviderById(it)
                if (provider != null) {
                    call.respond(HttpStatusCode.OK, provider.toResponse())
                }
            }
        }

        post {
            val request = call.receive<ProviderRequest>()
            service.addProvider(request.toDomain())
            call.respond(HttpStatusCode.Created, "")
        }

        put("{id}") {
            val id = call.parameters["id"]
                ?: return@put call.respond(HttpStatusCode.BadRequest, "ID inválido")

            val request = call.receive<ProviderRequest>()
            service
        }

        delete("{id}") {
            val id = call.parameters["id"]
                ?: return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido")

            val request = call.receive<ProviderRequest>()
            service.removeProvider(request.toDomain())
        }
    }
}