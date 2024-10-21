package com.example.plugins

import com.example.domain.repository.ChannelRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UserUseCase
import com.example.routing.channelRouting
import com.example.routing.userRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val API_URL = "api"
private const val API_VERSION = "v1"

fun Application.configureRouting() {

    val userRepository by inject<UserRepository>()
    val channelRepository by inject<ChannelRepository>()

    routing {
        route(API_URL) {
            route(API_VERSION) {
                userRouting(userRepository)
                channelRouting(channelRepository)
            }
        }
    }

    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "$status", status = HttpStatusCode.NotFound)
        }
        status(HttpStatusCode.BadRequest) { call, status ->
            call.respondText(text = "$status", status = HttpStatusCode.BadRequest)
        }
        /*status(HttpStatusCode.InternalServerError) { call, status ->
            call.respondText(text = "$status", status = HttpStatusCode.InternalServerError)
        }*/
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
}