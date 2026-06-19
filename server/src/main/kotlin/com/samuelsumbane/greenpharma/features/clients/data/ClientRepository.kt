package com.samuelsumbane.greenpharma.features.clients.data

import com.samuelsumbane.greenpharma.model.ClientRequest
import com.samuelsumbane.greenpharma.model.ClientResponse
import com.samuelsumbane.greenpharma.model.Genere
import com.samuelsumbane.greenpharma.model.toResponse

object ClientRepository {
    var clients = mutableListOf(
        ClientResponse("rera", "Marta", "", "", 0L, Genere.Male, "", "", "", true),
        ClientResponse("fsdvef", "Miriam", "", "", 0L, Genere.Male, "", "", "", true),
    )

//    fun getClients() = clients

    fun getClientById(clientId: String): ClientResponse? = clients.firstOrNull { it.id == clientId }


    fun insert(client: ClientRequest) {
        clients.add(client.toResponse())
    }

    fun delete(client: ClientResponse): Boolean {
        // Soft delete
        val foundClient = getClientById(client.id)
        foundClient?.let { client.copy(active = false) }
        return foundClient != null
    }
}