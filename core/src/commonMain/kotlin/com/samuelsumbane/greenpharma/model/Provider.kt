package com.samuelsumbane.greenpharma.model

data class Provider(
    val id: String,
    val name: String,
    val contact: String,
    val telephone: String,
    val email: String,
    val endereco: String,
    val ative: Boolean
)