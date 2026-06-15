package com.samuelsumbane.greenpharma.features.providers.domain

import com.samuelsumbane.greenpharma.features.providers.data.ProviderRepository
import com.samuelsumbane.greenpharma.model.Provider

class ProviderService(
    private val repo: ProviderRepository
) {
    suspend fun listAllProviders(): List<Provider> {
        return repo.getAll()
    }
}