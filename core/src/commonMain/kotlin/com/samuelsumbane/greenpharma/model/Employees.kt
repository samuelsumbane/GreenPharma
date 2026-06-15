package com.samuelsumbane.greenpharma.model

data class Employees(
    val id: String,
    val name: String,
    val role: Role,
    val username: String,
    val password_hash: String,
    val telephone: String,
    val email: String,
    val active: Boolean
)