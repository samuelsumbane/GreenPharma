//package com.samuelsumbane.greenpharma
//
//import io.ktor.server.application.Application
//import org.jetbrains.exposed.sql.Database
//import org.jetbrains.exposed.sql.SchemaUtils
//import org.jetbrains.exposed.sql.transactions.transaction
//
////fun Application.configureDatabase() {
////    Database.connect("jdbc:sqlite:pharmabook.db", driver = "org.sqlite.JDBC")
////
////    transaction {
////        SchemaUtils.create(UserTable, PaymentTable)
////    }
////}