package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*

fun Application.configureHTTP() {
    install(CORS) {
//        allowMethod(HttpMethod.Options)
//        allowMethod(HttpMethod.Put)
//        allowMethod(HttpMethod.Delete)
//        allowMethod(HttpMethod.Patch)
//        allowHeader(HttpHeaders.Authorization)
//        allowHeader("MyCustomHeader")
//        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.

        // TODO: Разрешаем все запросы
        allowHeaders { true }
        HttpMethod.DefaultMethods.forEach { allowMethod(it) }
        allowNonSimpleContentTypes = true
        anyHost()
    }
}
