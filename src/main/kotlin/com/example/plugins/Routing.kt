package com.example.plugins

import com.example.routing.userRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val API_URL = "api"
private const val API_VERSION = "v1"

fun Application.configureRouting() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "$status", status = HttpStatusCode.NotFound)
        }
        status(HttpStatusCode.BadRequest) { call, status ->
            call.respondText(text = "$status", status = HttpStatusCode.BadRequest)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        route(API_URL) {
            route(API_VERSION) {
                userRouting()
            }
        }
    }
}