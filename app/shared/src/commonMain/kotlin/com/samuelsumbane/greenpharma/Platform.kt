package com.samuelsumbane.greenpharma

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform