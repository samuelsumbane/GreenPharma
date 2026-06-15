package com.samuelsumbane.greenpharma.model

data class SalesItems(
    val id: String,
    val saleId: String,
    val productId: String,
    val loteId: String,
    val quantity: Int,
    val unityPrice: Double,
    val descount: Double,
    val subtotal: Double
)
