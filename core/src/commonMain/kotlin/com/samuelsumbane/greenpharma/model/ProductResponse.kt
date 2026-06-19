package com.samuelsumbane.greenpharma.model

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class ProductResponse(
    val id: String,
    val categoryId: Int,
    val comercialName: String,
    val genericName: String,
    val principioAtivo: String,
    val fabricante: String,
    val barCode: String,
    val unity: ProductUnity,
    val sellPrice: Double,
    val costPrice: Double,
    val minStock: Int,
    val actualStock: Int,
    val active: Boolean
)

@Serializable
data class ProductRequest(
    val categoryId: Int,
    val comercialName: String,
    val genericName: String,
    val principioAtivo: String,
    val fabricante: String,
    val barCode: String,
    val unity: ProductUnity,
    val sellPrice: Double,
    val costPrice: Double,
    val minStock: Int,
    val actualStock: Int,
)


fun ProductRequest.toResponse() = ProductResponse(
    id = "dlsffasl ${Random.nextInt()}",
    categoryId = this.categoryId,
    comercialName = this.comercialName,
    genericName = this.genericName,
    principioAtivo = this.principioAtivo,
    fabricante = this.fabricante,
    barCode = this.barCode,
    unity = this.unity,
    sellPrice = this.sellPrice,
    costPrice = this.costPrice,
    minStock = this.minStock,
    actualStock = this.actualStock,
    active = true
)