package com.samuelsumbane.greenpharma.model

import kotlinx.serialization.Serializable
import kotlin.random.Random

data class ClientResponse(
    val id: String = "",
    val name: String,
    val telephone: String,
    val email: String? = null,
    val birthDay: Long? = null,
    val genere: Genere,
    val address: String,
    val allergies: String,
    val observations: String,
    val active: Boolean = true
)



fun ClientRequest.toResponse() = ClientResponse(
    id = "clientfkls ${Random.nextInt()}",
    name = this.name,
    telephone = this.telephone,
    email = this.email,
    birthDay = this.birthDay,
    genere = this.genere,
    address = this.address,
    allergies = this.allergies,
    observations = this.observations,
    active = true
)


@Serializable
data class ClientRequest(
    val name: String,
    val telephone: String,
    val email: String? = null,
    val birthDay: Long? = null,
    val genere: Genere,
    val address: String,
    val allergies: String,
    val observations: String,
)


