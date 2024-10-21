package com.example.data.repository

import com.example.data.tables.UsersTable
import com.example.domain.model.UserDTO
import com.example.domain.repository.UserRepository
import com.example.plugins.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserRepositoryImpl: UserRepository {
    override suspend fun findByUid(uid: Long): UserDTO? {
        return dbQuery {
            UsersTable.selectAll().where { UsersTable.uid.eq(uid) }.singleOrNull()?.toUserDTO()
        }
    }

    override suspend fun create(user: UserDTO): UserDTO? {
        return dbQuery {
            UsersTable.insertReturning { table ->
                table[uid] = user.uid
                table[firstName] = user.firstName
                table[lastName] = user.lastName
                table[username] = user.username
            }.singleOrNull()?.toUserDTO()
        }
    }

    override suspend fun delete(uid: Long): Boolean {
        return dbQuery {
            UsersTable.deleteWhere { UsersTable.uid.eq(uid) } == 1
        }
    }
}

private fun ResultRow.toUserDTO(): UserDTO {
    return UserDTO(
        uid = this[UsersTable.uid],
        firstName = this[UsersTable.firstName],
        lastName = this[UsersTable.lastName],
        username = this[UsersTable.username],
    )
}

