package com.example.domain.usecase

import com.example.domain.model.UserDTO
import com.example.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {
    suspend fun createUser(user: UserDTO): UserDTO? {
        return repository.create(user)
    }

    suspend fun findUserByUid(uid: Long): UserDTO? {
        return repository.findByUid(uid)
    }

    suspend fun deleteUser(uid: Long): Boolean {
        return repository.delete(uid)
    }
}