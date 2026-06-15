package com.samuelsumbane.greenpharma.model

data class Product(
    val id: String,
    val categoryId: Int,
    val comercialName: String,
    val genericName: String,
    val principioAtivo: String,
    val fabricante: String,
    val barCode: String,
    val unity: String, // Unidade, caixa, frasco, ml, mg
    val sellPrice: Double,
    val costPrice: Double,
    val minStock: Int,
    val actualStock: Int,
    val active: Boolean
)
