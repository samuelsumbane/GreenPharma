package com.samuelsumbane.greenpharma.features.clients.data

import com.samuelsumbane.greenpharma.model.Client
import com.samuelsumbane.greenpharma.model.Genere

object ClientRepository {
    var clients = mutableListOf(
        Client("rera", "Marta", "", "", 0L, Genere.Male, "", "", "", true),
        Client("fsdvef", "Miriam", "", "", 0L, Genere.Male, "", "", "", true),
    )

//    fun getClients() = clients

    fun getClientById(clientId: String): Client? = clients.firstOrNull { it.id == clientId }


    fun insert(client: Client) {
        clients.add(client)
    }

    fun delete(client: Client): Boolean {
        // Soft delete
        val foundClient = getClientById(client.id)
        foundClient?.let { client.copy(active = false) }
        return foundClient != null
    }
}