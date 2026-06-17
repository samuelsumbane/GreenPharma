package com.samuelsumbane.greenpharma.features.clients.domain

import com.samuelsumbane.greenpharma.ApiResult
import com.samuelsumbane.greenpharma.features.clients.data.ClientRepository
import com.samuelsumbane.greenpharma.model.Client

class ClientService(private val repo: ClientRepository) {

    fun getAllClients(): List<Client> = repo.clients

    fun addClient(client: Client) {
        repo.insert(client)
    }

    fun getClientById(clientId: String): ApiResult<Client> {
        val foundClient = repo.getClientById(clientId)
        return  if (foundClient != null) {
            ApiResult.Success(data = foundClient)
        } else {
            ApiResult.Error(message = "Nenhum cliente encontrado.")
        }
    }

    fun delete(client: Client) {
        repo.delete(client)
    }
}
