package com.samuelsumbane.greenpharma

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cors.routing.CORS

fun Application.configureHTTP() {
    install(CORS) {
        allowHost("0.0.0.0:8080")
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("MyCustomHeader")
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.

        allowCredentials = true

        allowHost("127.0.0.1:8080", schemes = listOf("http"))
        allowHost("0.0.0.0:8080", schemes = listOf("http"))
        allowHost("10.0.2.2:8080", schemes = listOf("http"))
        allowHost("10.0.2.2:8080")
        allowHost("")
    }
}