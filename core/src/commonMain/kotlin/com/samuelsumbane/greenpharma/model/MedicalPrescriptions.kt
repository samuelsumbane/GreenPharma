package com.samuelsumbane.greenpharma.model

// receitas medicas
data class MedicalPrescriptions(
    val id: String,
    val clientId: String,
    val doctorsName: String,
    val issueDate: Long,
    val expirationDate: Long,
    val observations: String,
    val imagemUrl: String
)
