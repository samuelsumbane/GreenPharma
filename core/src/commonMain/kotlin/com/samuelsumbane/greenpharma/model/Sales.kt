package com.samuelsumbane.greenpharma.model

data class Sales(
    val id: String,
    val clientId: String? = null,
    val employeeId: String,
    val renenue: String, // receita
    val cashMoneyId: String,
    val saleDate: Long,
    val subTotal: Double,
    val descount: Double,
    val total: Double,
    val status: SaleStatus,
    val observations: String
)

enum class SaleStatus(stringValue: String) {
    Peding("Pendente"),
    Done("Concluida"),
    Canceled("Cancelada")
}