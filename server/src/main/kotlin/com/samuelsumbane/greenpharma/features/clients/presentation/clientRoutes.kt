package com.samuelsumbane.greenpharma.features.clients.presentation

import com.samuelsumbane.greenpharma.ApiResult
import com.samuelsumbane.greenpharma.features.clients.domain.ClientService
import com.samuelsumbane.greenpharma.model.ClientRequest
import com.samuelsumbane.greenpharma.model.toDomain
import com.samuelsumbane.greenpharma.model.toResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.clientRoute(clientService: ClientService) {
    route("/clients") {

        get {
            val clients = clientService.getAllClients()
            call.respond(HttpStatusCode.OK, clients)
        } 

        get("{id}") {
            val clientId = call.parameters["id"]
                ?: return@get call.respond(HttpStatusCode.NotFound, "Nenhum cliente encontrado.")

            when (val result = clientService.getClientById(clientId)) {
                is ApiResult.Success -> call.respond(HttpStatusCode.OK, result.data)
                is ApiResult.Error -> call.respond(result.statusCode, result.message)
            }
        }

        delete("{id}") {
            val clientId = call.parameters["id"]
                ?: return@delete call.respond(HttpStatusCode.NotFound, "Nenhum cliente encontrado.")

            val result = clientService.delete(clientId)
            when (result) {
                is ApiResult.Success -> call.respond(HttpStatusCode.OK, "Cliente eleminado com sucesso.")
                is ApiResult.Error -> call.respond(result.statusCode, result.message)
            }
//            call.respond(HttpStatusCode.OK, "Cliente adicionado com sucesso.")
        }


    }
}