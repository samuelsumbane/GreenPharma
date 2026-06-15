plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
}

group = "com.samuelsumbane.greenpharma"
version = "1.0.0"
application {
    mainClass = "com.samuelsumbane.greenpharma.ApplicationKt"
}

dependencies {
    api(projects.core)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    //
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.config.yaml)

    implementation("org.jetbrains.exposed:exposed-core:0.43.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.43.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")

    // BCrypt - password hash --------->>
    implementation("org.mindrot:jbcrypt:0.4")
    // Authentication --->
    implementation("io.ktor:ktor-server-auth-jwt:3.3.0")


    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("jakarta.mail:jakarta.mail-api:2.1.3") // API
    implementation("org.eclipse.angus:jakarta.mail:2.0.3") // Implementação
}