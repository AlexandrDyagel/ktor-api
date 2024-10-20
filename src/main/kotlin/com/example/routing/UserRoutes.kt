package com.example.routing

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    get("/user") {
        call.respondText { "This is User routes" }
    }
}