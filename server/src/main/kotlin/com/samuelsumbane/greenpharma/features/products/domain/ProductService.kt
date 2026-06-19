package com.samuelsumbane.greenpharma.features.products.domain

import com.samuelsumbane.greenpharma.ApiResult
import com.samuelsumbane.greenpharma.features.products.data.ProductRepository
import com.samuelsumbane.greenpharma.model.ProductRequest
import com.samuelsumbane.greenpharma.model.ProductResponse
import io.ktor.http.HttpStatusCode

class ProductService(val repo: ProductRepository) {

    fun getAllProducts(): List<ProductResponse> {
        return repo.getProducts()
    }

    fun getProductById(productId: String): ProductResponse? = repo.getProductById(productId)

    fun addProduct(product: ProductRequest) {
        repo.createProduct(product)
    }

    fun deleteProduct(productId: String): ApiResult<Boolean> {
        // Soft delete
        return getProductById(productId)?.let {
            ApiResult.Success(data = true)
        } ?: run {
            ApiResult.Error(HttpStatusCode.NotFound, "Producto deletado com sucesso.")
        }
    }
}