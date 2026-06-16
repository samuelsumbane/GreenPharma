package com.samuelsumbane.greenpharma.features.providers.domain

import com.samuelsumbane.greenpharma.features.providers.data.ProviderRepository
import com.samuelsumbane.greenpharma.model.ProviderRequest
import com.samuelsumbane.greenpharma.model.Provider

class ProviderService(
    private val repo: ProviderRepository
) {
    fun listAllProviders(): List<Provider> {
        return repo.getAll()
    }

    fun findProviderById(id: String): Provider? {
        return repo.getById(id)
    }

    fun addProvider(provider: Provider) {
        repo.insertProvider(provider)
    }

//    fun updateProvider(provider: Provider) {
//        repo.
//    }
    fun removeProvider(provider: Provider) {
        repo.deleteProvider(provider)
    }
}