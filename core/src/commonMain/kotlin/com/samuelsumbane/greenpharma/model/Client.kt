package com.samuelsumbane.greenpharma.model

import kotlinx.serialization.Serializable

data class Client(
    val id: String,
    val name: String,
    val telephone: String,
    val email: String? = null,
    val birthDay: Long? = null,
    val genere: Genere,
    val address: String,
    val allergies: String,
    val observations: String,
    val active: Boolean
)


@Serializable
data class ClientResponse(
    val id: String,
    val name: String,
    val telephone: String,
    val email: String? = null,
    val birthDay: Long? = null,
    val genere: Genere,
    val address: String,
    val allergies: String,
    val observations: String,
    val active: Boolean
)

fun Client.toResponse() = ClientResponse(
    id = this.id,
    name = this.name,
    telephone = this.telephone,
    email = this.email,
    birthDay = this.birthDay,
    genere = this.genere,
    address = this.address,
    allergies = this.allergies,
    observations = this.observations,
    active = this.active
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