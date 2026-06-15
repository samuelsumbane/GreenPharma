package com.samuelsumbane.greenpharma.model

data class Clients(
    val id: String,
    val name: String,
    val telephone: String,
    val email: String? = null,
    val birthDay: Long? = null,
    val genere: Genere,
    val address: String,
    val allergies: String,
    val observations: String,
    val active: String
)