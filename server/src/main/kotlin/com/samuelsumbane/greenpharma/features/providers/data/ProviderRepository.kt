package com.samuelsumbane.greenpharma.features.providers.data

import com.samuelsumbane.greenpharma.model.Provider
import kotlinx.coroutines.runBlocking

object ProviderRepository {
    val allProviders = mutableListOf(
        Provider(id = "ufds", name = "fd", contact = "", telephone = "", email = "", address = "", true),
        Provider(id = "ioo", name = "gdfe", contact = "", telephone = "", email = "", address = "", true),
    )
    fun getAll(): List<Provider> = allProviders

    fun getById(id: String): Provider? {
        return  allProviders.firstOrNull { it.id == id }
    }

    fun insertProvider(provider: Provider) {
        allProviders.add(provider)
    }

//    fun updateProvider(provider: Provider) {
//        allProviders.g
//    }
    fun deleteProvider(provider: Provider) {
        allProviders.remove(provider)
    }
}