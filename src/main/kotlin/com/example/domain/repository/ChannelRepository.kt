package com.example.domain.repository

import com.example.domain.model.ChannelDTO

interface ChannelRepository {
    suspend fun findAll(): List<ChannelDTO>
    suspend fun findByUid(uid: Long): ChannelDTO?
    suspend fun findByOwner(ownerUid: Long): List<ChannelDTO>
    suspend fun create(channel: ChannelDTO): ChannelDTO?
    suspend fun delete(uid: Long): Boolean
}