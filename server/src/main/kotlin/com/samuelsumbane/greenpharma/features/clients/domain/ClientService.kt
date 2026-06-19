package com.samuelsumbane.greenpharma.features.clients.domain

import com.samuelsumbane.greenpharma.ApiResult
import com.samuelsumbane.greenpharma.features.clients.data.ClientRepository
import com.samuelsumbane.greenpharma.model.ClientRequest
import com.samuelsumbane.greenpharma.model.ClientResponse
import io.ktor.http.HttpStatusCode

class ClientService(private val repo: ClientRepository) {

    fun getAllClients(): List<ClientResponse> = repo.clients

    fun addClient(client: ClientRequest) {
        repo.insert(client)
    }

    fun getClientById(clientId: String): ApiResult<ClientResponse> {
        val foundClient = repo.getClientById(clientId)
        return  if (foundClient != null) {
            ApiResult.Success(data = foundClient)
        } else {
            ApiResult.Error(HttpStatusCode.NotFound, message = "Nenhum cliente encontrado.")
        }
    }

    fun delete(clientId: String): ApiResult<Boolean> {
        return repo.getClientById(clientId)?.let {
            repo.delete(it)
            ApiResult.Success(data = true)
        } ?: run {
            ApiResult.Error(HttpStatusCode.NotFound, "Cliente não encontrado.")
        }
    }
}
