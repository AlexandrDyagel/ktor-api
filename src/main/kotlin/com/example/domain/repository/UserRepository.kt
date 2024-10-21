package com.example.domain.repository

import com.example.domain.model.UserDTO

interface UserRepository {
    suspend fun findByUid(uid: Long): UserDTO?
    suspend fun create(user: UserDTO): UserDTO?
    suspend fun delete(uid: Long): Boolean
}