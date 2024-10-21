package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val uid: Long,
    val firstName: String,
    val lastName: String?,
    val username: String?
)
