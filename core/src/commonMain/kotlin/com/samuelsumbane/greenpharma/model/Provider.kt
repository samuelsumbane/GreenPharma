package com.samuelsumbane.greenpharma.model

import kotlinx.serialization.Serializable

data class Provider(
    val id: String = "",
    val name: String,
    val contact: String,
    val telephone: String,
    val email: String,
    val address: String,
    val ative: Boolean = true
)

@Serializable
data class ProviderRequest(
    val name: String,
    val contact: String,
    val telephone: String,
    val email: String,
    val address: String,
)

fun ProviderRequest.toDomain() = Provider(
    name = this.name,
    contact = this.contact,
    telephone = this.telephone,
    email = this.email,
    address = this.address,
)

@Serializable
data class ProviderResponse(
    val id: String,
    val name: String,
    val contact: String,
    val telephone: String,
    val email: String,
    val address: String,
    val active: Boolean
)

fun Provider.toResponse() = ProviderResponse(
    id = this.id,
    name = this.name,
    contact = this.contact,
    telephone = this.telephone,
    email = this.email,
    address = this.address,
    active = this.ative
)


