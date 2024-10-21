package com.example.routing

import com.example.domain.model.UserDTO
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.UserUseCase
import com.example.resource.Users
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(
    userRepository: UserRepository
) {
    val userUseCase = UserUseCase(userRepository)

    get<Users.Uid> {
        val user = userUseCase.findUserByUid(it.uid)
        if (user != null) {
            call.respond(user)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    post<Users> {
        val user = call.receive<UserDTO>()
        userUseCase.createUser(user)?.let {responseUser ->
            call.respond(status = HttpStatusCode.Created, message = responseUser)
        }
    }

    delete<Users.Uid> {
        val deleted = userUseCase.deleteUser(it.uid)
        if (deleted) {
            call.respond(status = HttpStatusCode.OK, message = "User with uid ${it.uid} has been deleted")
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}