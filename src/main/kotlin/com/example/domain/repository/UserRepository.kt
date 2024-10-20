package com.example.domain.repository

import com.example.domain.model.UserDTO

interface UserRepository {
    suspend fun findByUid(uid: Long): UserDTO?
    suspend fun create(user: UserDTO): Boolean
    suspend fun delete(user: UserDTO): Boolean
}