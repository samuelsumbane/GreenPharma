package com.samuelsumbane.greenpharma.model

data class Payments(
    val id: String,
    val saleId: String,
    val paymentMethod: PaymentMethod,
    val value: Double,
    val charge: Double,
    val reference: String, // transaction nr (mpesa)
)

enum class PaymentMethod(val stringValue: String) {
    Money("Dinheiro"),
    Card("Cartão")
}