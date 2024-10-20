package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UserTable: Table("users") {
    val uid = long("uid").uniqueIndex()
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50).nullable()
    val username = varchar("username", 50).uniqueIndex().nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(uid)
}