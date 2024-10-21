package com.example.data.repository

import com.example.data.tables.UserTable
import com.example.domain.model.UserDTO
import com.example.domain.repository.UserRepository
import com.example.plugins.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserRepositoryImpl: UserRepository {
    override suspend fun findByUid(uid: Long): UserDTO? {
        return dbQuery {
            UserTable.selectAll().where { UserTable.uid.eq(uid) }.singleOrNull()?.toUserDTO()
        }
    }

    override suspend fun create(user: UserDTO): UserDTO? {
        return dbQuery {
            UserTable.insertReturning {table ->
                table[uid] = user.uid
                table[firstName] = user.firstName
                table[lastName] = user.lastName
                table[username] = user.username
            }.singleOrNull()?.toUserDTO()
        }
    }

    override suspend fun delete(uid: Long): Boolean {
        return dbQuery {
            UserTable.deleteWhere { UserTable.uid.eq(uid) } == 1
        }
    }

    private fun ResultRow.toUserDTO(): UserDTO =
        UserDTO(
            uid = this[UserTable.uid],
            firstName = this[UserTable.firstName],
            lastName = this[UserTable.lastName],
            username = this[UserTable.username],
        )

}