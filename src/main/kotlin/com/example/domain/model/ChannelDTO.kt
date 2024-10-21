package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ChannelDTO(
    val uid: Long,
    val title: String,
    val description: String?,
    val username: String,
    val image: String,
    val inviteLink: String,
    val ownerUid: Long
)
