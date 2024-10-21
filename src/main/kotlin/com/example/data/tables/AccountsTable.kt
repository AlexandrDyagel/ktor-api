package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object AccountsTable : Table("accounts") {
    val accountId = integer("account_id").autoIncrement() // уникальный идентификатор аккаунта
    val currencyCode = varchar("currency_code", 3) // код валюты для аккаунта (например, USD, EUR, RUB)
    val currentBalance = long("current_balance").default(0L) // текущий баланс аккаунта в минимальных единицах валюты (например, в центах).
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)

    val userUid = reference("user_uid", UsersTable.uid) // внешний ключ на таблицу пользователей

    override val primaryKey = PrimaryKey(accountId)
}