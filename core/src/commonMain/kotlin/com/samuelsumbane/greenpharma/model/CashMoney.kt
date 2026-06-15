package com.samuelsumbane.greenpharma.model

data class CashMoney(
    val id: String,
    val employeeId: String,
    val opening: Long,
    val closing: Long,
    val openingBalance: Double,
    val closingBalance: Double,
    val observations: String,
    val status: CashMoneyStatus
)

enum class CashMoneyStatus(val stringName: String) {
    Open("Aberto"),
    Closed("Fechado")
}
