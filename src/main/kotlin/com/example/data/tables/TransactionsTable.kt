package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TransactionsTable: Table("transactions") {
    val transactionId = integer("transaction_id").autoIncrement() // уникальный идентификатор транзакции.
    val transactionType = varchar("transaction_type", 20) // ENUM('deposit', 'withdrawal', 'refund', 'adjustment') NOT NULL, -- тип транзакции (например, deposit — пополнение, withdrawal — снятие, refund — возврат, adjustment — корректировка)
    val amount = long("amount") // сумма транзакции в минимальных единицах валюты (положительное значение для пополнений и отрицательное для снятий)
    val description = varchar("description", 255).nullable() // описание транзакции, которое может включать детали о том, зачем была выполнена транзакция
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    val accountId = reference("account_id", AccountsTable.accountId) // ссылка на аккаунт, к которому относится транзакция

    override val primaryKey = PrimaryKey(transactionId)
}