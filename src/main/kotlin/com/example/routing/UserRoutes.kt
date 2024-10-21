package com.example.routing

import com.example.domain.model.UserDTO
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UserUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val ROOT_PATH = "users"

fun Route.userRouting(
    userRepository: UserRepository
) {
    val userUseCase = UserUseCase(userRepository)

    route(ROOT_PATH) {
        get("{uid}") {
            call.parameters["uid"]?.let {
                val user = userUseCase.findUserByUid(it.toLong())
                if (user != null) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }

        post {
            val user = call.receive<UserDTO>()
            userUseCase.createUser(user)?.let {
                call.respond(status = HttpStatusCode.Created, message = it)
            }
        }

        delete("{uid}") {
            call.parameters["uid"]?.let {
                if(userUseCase.deleteUser(it.toLong())) {
                    call.respond(status = HttpStatusCode.OK, message = "User with uid $it has been deleted")
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}