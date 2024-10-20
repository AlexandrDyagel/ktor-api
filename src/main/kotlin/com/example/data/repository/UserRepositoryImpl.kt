package com.example.data.repository

import com.example.domain.model.UserDTO
import com.example.domain.repository.UserRepository

object UserRepositoryImpl: UserRepository {
    override suspend fun findByUid(uid: Long): UserDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun create(user: UserDTO): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: UserDTO): Boolean {
        TODO("Not yet implemented")
    }
}