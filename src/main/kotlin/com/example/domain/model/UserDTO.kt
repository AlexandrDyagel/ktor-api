package com.example.domain.model


data class UserDTO(
    val uid: Long,
    val firstName: String,
    val lastName: String?,
    val username: String?
)
