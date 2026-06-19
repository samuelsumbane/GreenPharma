package com.samuelsumbane.greenpharma.features.products.data

import com.samuelsumbane.greenpharma.ApiResult
import com.samuelsumbane.greenpharma.model.ProductRequest
import com.samuelsumbane.greenpharma.model.ProductResponse
import com.samuelsumbane.greenpharma.model.ProductUnity
import com.samuelsumbane.greenpharma.model.toResponse
import io.ktor.http.HttpStatusCode

object ProductRepository {

    val products = mutableListOf(
        ProductResponse("gttdg", 1, "", "", "", "", "", ProductUnity.Unity, 12.0, 8.0, 3, 24, true),

        ProductResponse("faef", 1, "", "", "", "", "", ProductUnity.Box, 12.0, 8.0, 3, 24, true),
    )

    fun getProducts(): List<ProductResponse> = products

    fun getProductById(productId: String): ProductResponse? = products.firstOrNull { it.id == productId}

    fun createProduct(product: ProductRequest) {
        products.add(product.toResponse())
    }

    fun deleteProduct(productId: String): ApiResult<Boolean> {
        return getProductById(productId)?.let { product ->
//            product.copy(active = false)
//            products.
//            products.first { id == productId }.copy()
            ApiResult.Success(data = true)
        } ?: run {
            ApiResult.Error(HttpStatusCode.NotFound, "Producto não encontrado.")
        }
    }

}