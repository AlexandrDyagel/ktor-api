package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ChannelsTable : Table("channels") {
    val uid = long("uid").uniqueIndex()
    val title = varchar("title", 50)
    val description = varchar("description", 250).nullable()
    val username = varchar("username", 50).uniqueIndex()
    val image = varchar("image", 100)
    val inviteLink = varchar("inviteLink", 100)
    val ownerUid = long("owner_uid")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(uid)
}