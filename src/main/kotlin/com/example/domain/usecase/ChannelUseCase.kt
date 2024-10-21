package com.example.domain.usecase

import com.example.domain.model.ChannelDTO
import com.example.domain.repository.ChannelRepository

class ChannelUseCase(
    private val repository: ChannelRepository
) {
    suspend fun getChannels(): List<ChannelDTO> {
        return repository.findAll()
    }

    suspend fun findChannelByUid(uid: Long): ChannelDTO? {
        return repository.findByUid(uid)
    }

    suspend fun findChannelsByOwner(ownerUid: Long): List<ChannelDTO> {
        return repository.findByOwner(ownerUid)
    }

    suspend fun createChannel(channel: ChannelDTO): ChannelDTO? {
        return repository.create(channel)
    }

    suspend fun deleteChannel(uid: Long): Boolean {
        return repository.delete(uid)
    }
}