package com.example.routing

import com.example.domain.model.ChannelDTO
import com.example.domain.repository.ChannelRepository
import com.example.domain.usecase.ChannelUseCase
import com.example.resource.Channels
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.post

fun Route.channelRouting(
    channelRepository: ChannelRepository
) {
    val channelUseCase = ChannelUseCase(channelRepository)

    get<Channels> {
        val channels = channelUseCase.getChannels()
        call.respond(channels)
    }
    get<Channels.Uid> {
            val channel = channelUseCase.findChannelByUid(it.uid)
            if (channel != null) {
                call.respond(channel)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
    }

    get<Channels.OwnerUid> {
            val channel = channelUseCase.findChannelByOwner(it.ownerUid)
            if (channel != null) {
                call.respond(channel)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
    }

    post<Channels> {
        val channel = call.receive<ChannelDTO>()
        channelUseCase.createChannel(channel)?.let { responseChannel ->
            call.respond(responseChannel)
        }
    }
}