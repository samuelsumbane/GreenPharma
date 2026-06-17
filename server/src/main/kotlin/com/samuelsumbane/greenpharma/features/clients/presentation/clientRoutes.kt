package com.samuelsumbane.greenpharma.features.clients.presentation

import com.samuelsumbane.greenpharma.ApiResult
import com.samuelsumbane.greenpharma.features.clients.domain.ClientService
import com.samuelsumbane.greenpharma.model.toResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.clientRoute(clientService: ClientService) {
    route("/clients") {

        get {
            val clients = clientService.getAllClients()
            val clientsResponse = clients.map { it.toResponse() }
            call.respond(HttpStatusCode.OK, clientsResponse)
        }

        get("{id}") {
            val clientId = call.parameters["id"]
                ?: return@get call.respond(HttpStatusCode.NotFound, "Nenhum cliente encontrado.")
            when (val result = clientService.getClientById(clientId)) {
                is ApiResult.Success -> call.respond(HttpStatusCode.OK, result.data.toResponse())
                is ApiResult.Error -> call.respond(HttpStatusCode.NotFound, "Nenhum cliente encontrado.")
            }
        }
    }
}